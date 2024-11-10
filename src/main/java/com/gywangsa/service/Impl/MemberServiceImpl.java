package com.gywangsa.service.Impl;


import com.gywangsa.domain.*;
import com.gywangsa.dto.MemberDTO;
import com.gywangsa.repository.*;
import com.gywangsa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final MemberAuthorityRepository memberAuthorityRepository;

    private final OrderInfoRepository orderInfoRepository;

    private final CartRepository cartRepository;

    private final OrderDtlRepository orderDtlRepository;

    private final LikeChkRepository likeChkRepository;

    private LocalDateTime now = LocalDateTime.now();


    //카카오 accessToken
    private String getKakaoAccessToken(String accessToken) {

        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============카카오 회원 accessToken============");

        //url 호출
        String kakaoGetUserUrl = "https://kapi.kakao.com/v2/user/me";

        //헤더 지정
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(kakaoGetUserUrl).build();

        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUri(), HttpMethod.GET, entity, LinkedHashMap.class);

        log.info(responseEntity);

        LinkedHashMap<String, LinkedHashMap> bodyMap = responseEntity.getBody();

        //LinkedHashMap<String, String> kakaoAccount = bodyMap.get("properties");

        //String userId = kakaoAccount.get("nickname");

        String userId = String.valueOf(bodyMap.get("id"));

        log.info("--------------------------------------------");
        log.info("-------------MemberServiceImpl--------------");
        log.info(bodyMap);
        log.info("userId : " + userId);

        return userId;
    }


    //카카오 회원 가입
    private Member makeKakaoMember(String userId) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============카카오 회원 가입============");

        String password = makePassword();

        Member member = Member.builder()
                .userId(userId)
                .pwd(passwordEncoder.encode(password))
                .name("")
                .email("")
                .phone("")
                .addrNo("")
                .addr("")
                .addrDtl("")
                .sexCd("")
                .pwdYn('N')
                .pwdDate(now)
                .loginDate(now)
                .joinType("카카오")
                .build();

        return member;
    }

    //패스워드 생성
    private String makePassword() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append((char) ((int) (Math.random() * 55) + 65));
        }
        return buffer.toString();
    }


    //카카오 로그인 회원 저장
    @Override
    public MemberDTO getKaKaoMember(String accessToken) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============카카오 회원 수정============");

        //accessToken 을 이용해서 사용자 정보 가져오기
        String userId = getKakaoAccessToken(accessToken);

        Optional<Member> result = memberRepository.findById(userId);

        //회원 정보가 있을 경우
        if (result.isPresent()) {
            log.info("회원 존재");
            MemberDTO dto = entityToDTO(result.get());
            return dto;
        }

        //회원 정보가 없을 경우
        log.info("회원 없음");
        Member member = makeKakaoMember(userId);

        MemberAuthority ma = MemberAuthority.builder()
                .userId(userId)
                .pwd(member.getPwd())
                .brandCd(0L)
                .brandNm("")
                .roleNm("USER")
                .startDate(now)
                .endDate(now.plusMonths(24))
                .note("정보 수정 필요")
                .build();

        memberRepository.save(member);
        memberAuthorityRepository.save(ma);

        MemberDTO dto = entityToDTO(member);

        return dto;
    }


    //일반 회원 가입
    @Override
    public String joinMember(MemberDTO memberDTO) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============일반 회원 가입============");
        Member member = dtoMember(memberDTO);

        member.changePwd(passwordEncoder.encode(memberDTO.getPwd()));
        member.changeJoinType("홈페이지");
        member.changeApiKey("");
        member.changePwdYn('N');
        member.changePwdDate(now);
        member.changeLoginDate(now);

        MemberAuthority ma = MemberAuthority.builder()
                .userId(memberDTO.getUserId())
                .pwd(member.getPwd())
                .roleNm("USER")
                .brandCd(0L)
                .brandNm("")
                .startDate(now)
                .endDate(now.plusMonths(24))
                .note("")
                .build();

        Member result = memberRepository.save(member);
        memberAuthorityRepository.save(ma);

        String userId = member.getUserId();

        return userId;
    }

    //브랜드 회원 가입
    @Override
    public String joinBrandMember(MemberDTO memberDTO, Long brandNo, String brandNm) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============브랜드 회원 가입============");

        Member member = dtoMember(memberDTO);

        member.changePwd(passwordEncoder.encode(memberDTO.getPwd()));
        member.changeJoinType("홈페이지");
        member.changeApiKey("");
        member.changePwdYn('N');
        member.changePwdDate(now);
        member.changeLoginDate(now);

        MemberAuthority ma = MemberAuthority.builder()
                .userId(memberDTO.getUserId())
                .pwd(member.getPwd())
                .roleNm("BRAND_MANAGER")
                .brandNm(brandNm)
                .brandCd(brandNo)
                .startDate(now)
                .endDate(now.plusMonths(24))
                .note("")
                .build();

        Member result = memberRepository.save(member);
        memberAuthorityRepository.save(ma);

        String userId = member.getUserId();

        return userId;
    }

    //회원 정보 수정
    @Override
    public void modifyUserInfo(MemberDTO memberDTO) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============회원 정보 수정============");

        Optional<Member> result = memberRepository.findById(memberDTO.getUserId());

        Member member = result.orElseThrow();

        if (!memberDTO.getPwd().equals(result.get().getPwd()) || memberDTO.getPwd() != "" || memberDTO.getPwd() != null) {
            member.changePwd(passwordEncoder.encode(memberDTO.getPwd()));
        }
        member.changeName(memberDTO.getName());
        member.changeEmail(memberDTO.getEmail());
        member.changePhone(memberDTO.getPhone());
        member.changeAddrNo(memberDTO.getAddrNo());
        member.changeAddr(memberDTO.getAddr());
        member.changeAddrDtl(memberDTO.getAddrDtl());
        member.changeSexCd(memberDTO.getSexCd());

        memberRepository.save(member);

        MemberAuthority memberAuthority = memberAuthorityRepository.selectUserAuthority(memberDTO.getUserId());

        if (!memberDTO.getPwd().equals(result.get().getPwd()) || memberDTO.getPwd() != "" || memberDTO.getPwd() != null) {
            memberAuthority.changePwd(member.getPwd());
        }
        memberAuthority.changeNote("");

        memberAuthorityRepository.save(memberAuthority);

    }

    //회원 탈퇴
    @Override
    public String removeMember(String userId) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============회원 탈퇴============");

        //주문 삭제
        Optional<OrderInfo> result = orderInfoRepository.selectOrderInfoMember(userId);

        if (!result.isEmpty()) {
            OrderInfo orderInfo = result.orElseThrow();

            List<OrderDtl> orderDtlList = orderDtlRepository.selectOrderDtlDTOByRemoveUserId(orderInfo);

            if (orderDtlList.size() != 0) {
                for (int i = 0; i < orderDtlList.size(); i++) {
                    if (!orderDtlList.get(i).getDeliStatus().equals("주문 완료") && !orderDtlList.get(i).getDeliStatus().equals("구매 완료")) {
                        return "실패";
                    }
                }
            }
            //주문 삭제
            orderInfo.setMember(null);
            orderInfoRepository.save(orderInfo);

        }


        //좋아요 삭제
        likeChkRepository.removePdLikeUserId(userId);

        //카트 삭제
        Optional<Cart> resultCart = cartRepository.selectCartMember(userId);

        if (!resultCart.isEmpty()) {

            Cart cart = resultCart.orElseThrow();

            cartRepository.deleteById(cart.getCartNo());
        }


        //권한 삭제
        memberAuthorityRepository.removeMemberAuthority(userId);

        //회원 삭제
        memberRepository.deleteById(userId);

        return "완료";
    }

    //회원 정보
    @Override
    public MemberDTO selectMemberInfo(String userId) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============회원 정보============");
        Member member = memberRepository.selectMemberId(userId);
        if (member == null) {
            member = new Member();
        }
        MemberDTO memberDTO = entityToDTO(member);
        return memberDTO;
    }

    //회원 비밀번호 수정
    @Override
    public void modifyMemberChangePassword(String userId, String pwd) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============비밀번호 수정============");

        String ecPwd = passwordEncoder.encode(pwd);

        Optional<Member> result = memberRepository.findById(userId);

        Member member = result.orElseThrow();

        member.changePwd(ecPwd);

        memberRepository.save(member);

        MemberAuthority memberAuthority = memberAuthorityRepository.selectUserAuthority(userId);

        memberAuthority.changePwd(ecPwd);

        memberAuthorityRepository.save(memberAuthority);
    }

    //회원 아이디 찾기
    @Override
    public String selectMemberFindUserID(String name, String email) {
        log.info("-------------------MemberServiceImpl-------------------");
        log.info("============아이디 찾기============");

        Optional<Member> result = memberRepository.selectMemberFindUserID(name, email);

        Member member = result.orElseThrow();

        if (member != null) {
            String userId = "";
            return userId;
        }

        String userId = member.getUserId();

        return userId;
    }
}
