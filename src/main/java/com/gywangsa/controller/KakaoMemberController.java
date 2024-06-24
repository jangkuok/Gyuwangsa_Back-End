package com.gywangsa.controller;

import com.gywangsa.dto.MemberAuthorityDTO;
import com.gywangsa.dto.MemberDTO;
import com.gywangsa.service.MemberAuthorityService;
import com.gywangsa.service.MemberService;
import com.gywangsa.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
public class KakaoMemberController {

    private final MemberService memberService;
    private final MemberAuthorityService memberAuthorityService;

    @GetMapping("/user/kakaoLogin")
    public Map<String, Object> getUserFromKakao(String accessToken){
        log.info("accessToken : " +accessToken );
        MemberDTO memberDTO = memberService.getKaKaoMember(accessToken);

        MemberAuthorityDTO memberAuthorityDTO = memberAuthorityService.selectByMemberAuthority(memberDTO.getUserId());

        Map<String, Object> claims = memberAuthorityDTO.getClaims();

        String jwtAccessToken = JWTUtil.generateToken(claims,10);
        String jwtRefreshToken = JWTUtil.generateToken(claims,60*24);

        claims.put("accessToken",jwtAccessToken);
        claims.put("refreshToken",jwtRefreshToken);

        return claims;
    }


}

