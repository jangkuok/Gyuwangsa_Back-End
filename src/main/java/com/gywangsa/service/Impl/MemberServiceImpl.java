package com.gywangsa.service.Impl;


import com.gywangsa.domain.Member;
import com.gywangsa.domain.MemberAuthority;
import com.gywangsa.dto.MemberDTO;
import com.gywangsa.repository.MemberAuthorityRepository;
import com.gywangsa.repository.MemberRepository;
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
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final MemberAuthorityRepository memberAuthorityRepository;

    private LocalDateTime now = LocalDateTime.now();


    private String getKakaoAccessToken(String accessToken){

        String kakaoGetUserUrl = "https://kapi.kakao.com/v2/user/me";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization","Bearer "+accessToken);
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(kakaoGetUserUrl).build();

        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.exchange(uriComponentsBuilder.toUri(), HttpMethod.GET,entity,LinkedHashMap.class);

        log.info(responseEntity);

        LinkedHashMap<String,LinkedHashMap> bodyMap = responseEntity.getBody();

        //LinkedHashMap<String, String> kakaoAccount = bodyMap.get("properties");

        //String userId = kakaoAccount.get("nickname");

        String userId = String.valueOf(bodyMap.get("id"));

        log.info("--------------------------------------------");
        log.info("-------------MemberServiceImpl--------------");
        log.info(bodyMap);
        log.info("userId : " +userId);

        return userId;
    }


    private Member makeKakaoMember(String userId){

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
    private String makePassword(){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10 ; i++){
            buffer.append((char)((int)(Math.random()*55)+65));
        }
        return buffer.toString();
    }


    //카카오 로그인 회원 저장
    @Override
    public MemberDTO getKaKaoMember(String accessToken) {

        //accessToken 을 이용해서 사용자 정보 가져오기
        String userId = getKakaoAccessToken(accessToken);

        Optional<Member> result = memberRepository.findById(userId);

        //회원 정보가 있을 경우
        if(result.isPresent()) {
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
        Member member = dtoMember(memberDTO);

        member.changePwd(passwordEncoder.encode(memberDTO.getPwd()));
        member.changeJoinType("");
        member.changeApiKey("");
        member.changePwdYn('N');
        member.changePwdDate(now);
        member.changeLoginDate(now);

        MemberAuthority ma = MemberAuthority.builder()
                .userId(memberDTO.getUserId())
                .pwd(member.getPwd())
                .roleNm("USER")
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

        Optional<Member> result = memberRepository.findById(memberDTO.getUserId());

        Member member = result.orElseThrow();

        member.changePwd(passwordEncoder.encode(memberDTO.getPwd()));
        member.changeName(memberDTO.getName());
        member.changeEmail(memberDTO.getEmail());
        member.changePhone(memberDTO.getPhone());
        member.changeAddrNo(memberDTO.getAddrNo());
        member.changeAddr(memberDTO.getAddr());
        member.changeAddrDtl(memberDTO.getAddrDtl());
        member.changeSexCd(memberDTO.getSexCd());

        memberRepository.save(member);

        MemberAuthority memberAuthority = memberAuthorityRepository.selectUserAuthority(memberDTO.getUserId());

        memberAuthority.changePwd(member.getPwd());
        memberAuthority.changeNote("");

        memberAuthorityRepository.save(memberAuthority);


//       memberRepository.modifyUserInfo(
//               memberDTO.getUserId(),
//               memberDTO.getPwd(),
//               memberDTO.getName(),
//               memberDTO.getEmail(),
//               memberDTO.getPhone(),
//               memberDTO.getAddrNo(),
//               memberDTO.getAddr(),
//               memberDTO.getAddrDtl(),
//               memberDTO.getSexCd());
    }
}
