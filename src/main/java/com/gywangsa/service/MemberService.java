package com.gywangsa.service;

import com.gywangsa.domain.Member;
import com.gywangsa.dto.MemberDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    //카카오 회원 정보 가입
    MemberDTO getKaKaoMember(String accessToken);

    //일반 회원 가입
    String joinMember(MemberDTO memberDTO);

    //브랜드 회원 가입
    String joinBrandMember(MemberDTO memberDTO, Long brandNo, String brandNm);

    //회원 정보 수정
    void modifyUserInfo(MemberDTO memberDTO);

    //비밀번호 초기화
    void modifyMemberChangePassword(String userId, String pwd);

    //회원 정보 찾기
    MemberDTO selectMemberInfo(String userId);

    //회원 아이디 찾기
    String selectMemberFindUserID(String name, String email);


    default MemberDTO entityToDTO(Member member){
        MemberDTO dto = new MemberDTO(
                member.getUserId(),
                member.getPwd(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getAddrNo(),
                member.getAddr(),
                member.getAddrDtl(),
                member.getSexCd(),
                member.getPwdYn(),
                member.getPwdDate(),
                member.getLoginDate(),
                member.getJoinType(),
                member.getApiKey()
        );
        return dto;
    }

    default Member dtoMember(MemberDTO memberDTO){
        Member member = Member.builder()
                .userId(memberDTO.getUserId())
                .pwd(memberDTO.getPwd())
                .name(memberDTO.getName())
                .phone(memberDTO.getPhone())
                .email(memberDTO.getEmail())
                .addrNo(memberDTO.getAddrNo())
                .addr(memberDTO.getAddr())
                .addrDtl(memberDTO.getAddrDtl())
                .sexCd(memberDTO.getSexCd())
                .pwdYn(memberDTO.getPwdYn())
                .pwdDate(memberDTO.getPwdDate())
                .loginDate(memberDTO.getLoginDate())
                .joinType(memberDTO.getJoinType())
                .apiKey(memberDTO.getApiKey())
                .build();
        return member;
    }
}
