package com.gywangsa.repository;

import com.gywangsa.domain.Authority;
import com.gywangsa.domain.Member;
import com.gywangsa.domain.MemberAuthority;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private MemberAuthorityRepository memberAuthorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMember() {

        LocalDateTime now = LocalDateTime.now();

        Member member  = Member.builder()
                .userId("test1")
                .pwd(passwordEncoder.encode("1111"))
                .name("test")
                .email("test@naver.com")
                .phone("01011111111")
                .addrNo("경기도")
                .addr("성남시")
                .addrDtl("분당구")
                .sexCd("남")
                .pwdYn('N')
                .pwdDate(now)
                .loginDate(now.plusMonths(12))
                .joinType("홈페이지")
                .apiKey("")
                .build();

//        Authority authority = Authority.builder()
//                .roleNm("USER")
//                .startDate(now)
//                .endDate(now.plusMonths(12))
//                .build();

        MemberAuthority ma = MemberAuthority.builder()
                .userId("test1")
                .pwd(member.getPwd())
                .roleNm("USER")
                .startDate(now)
                .endDate(now.plusMonths(12))
                .brandCd(2L)
                .brandNm("엘무드")
                .note("")
                .build();

        memberRepository.save(member);
        //authorityRepository.save(authority);
        memberAuthorityRepository.save(ma);

//        Authority authority = Authority.builder()
//                .roleNm("ADMIN")
//                .startDate(now)
//                .endDate(now.plusMonths(12))
//                .build();

        //authorityRepository.save(authority);
    }
}
