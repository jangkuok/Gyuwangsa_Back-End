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

    //회원 정보 수정
    void modifyUserInfo(MemberDTO memberDTO);


    default MemberDTO entityToDTO(Member member){
        MemberDTO dto = new MemberDTO(
                member.getUserId(),
                member.getPwd(),
                member.getName(),
                member.getPhone(),
                member.getEmail(),
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
