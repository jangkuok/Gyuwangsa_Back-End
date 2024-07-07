package com.gywangsa.controller;

import com.gywangsa.dto.MemberDTO;
import com.gywangsa.service.MemberAuthorityService;
import com.gywangsa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final MemberAuthorityService memberAuthorityService;

    //일반 회원 가입
    @PostMapping("/userJoin")
    public Map<String, String> joinMember(@RequestBody MemberDTO memberDTO){
        log.info("-------------------MemberController-------------------");
        log.info("============회원 가입============");
        memberService.joinMember(memberDTO);

        return Map.of("RESULT","SUCCESS");
    }

    //브랜드 회원 가입
    @PostMapping("/brandUserJoin")
    public Map<String, String> joinBrandMember(@RequestPart(value = "memberDTO") MemberDTO memberDTO,
                                          @RequestPart(value = "brandNo") String brandNo,
                                          @RequestPart(value = "brandNm") String brandNm){
        log.info("-------------------MemberController-------------------");
        log.info("============회원 가입============");
        memberService.joinBrandMember(memberDTO,Long.valueOf(brandNo),brandNm);

        return Map.of("RESULT","SUCCESS");
    }

    //회원 수정
    @PutMapping("/modify")
    public Map<String, String> modifyUserInfo(@RequestBody MemberDTO memberDTO){
        log.info("-------------------MemberController-------------------");
        log.info("============회원 수정============");
        log.info(memberDTO);
        memberService.modifyUserInfo(memberDTO);
        return Map.of("result", "수정완료");
    }

    //회원 정보 찾기
    @GetMapping("/info/{userId}")
    public MemberDTO selectMemberInfo(@PathVariable("userId")  String userId){
        log.info("-------------------MemberController-------------------");
        log.info("============회원 조회============");
        MemberDTO memberDTO = memberService.selectMemberInfo(userId);

        log.info(memberDTO);
        return memberDTO;
    }

    @GetMapping("/find/{name}/{email}")
    public String selectMemberFindUserID(@PathVariable("name")  String name,
                                            @PathVariable("email")  String email){
        log.info("-------------------MemberController-------------------");
        log.info("============아이디 찾기============");
        String userID = memberService.selectMemberFindUserID(name,email);

        log.info(userID);

        return userID;
    }

    @PutMapping("/modifyPassword")
    public Map<String, String> modifyMemberChangePassword(@RequestBody MemberDTO memberDTO){
        log.info("-------------------MemberController-------------------");
        log.info("============회원 비밀번호 수정============");

        log.info(memberDTO);

        memberService.modifyMemberChangePassword(memberDTO.getUserId(),memberDTO.getPwd());

        return Map.of("result", "수정완료");
    }

}
