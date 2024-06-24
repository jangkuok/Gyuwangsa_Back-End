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

        log.info("--------------------userJoin----------------------");
        memberService.joinMember(memberDTO);

        return Map.of("RESULT","SUCCESS");
    }

    @PutMapping("/modify")
    public Map<String, String> modifyUserInfo(@RequestBody MemberDTO memberDTO){
        log.info("----------------------modifyUserInfo----------------------");
        log.info(memberDTO);
        memberService.modifyUserInfo(memberDTO);
        return Map.of("result", "수정완료");
    }

}
