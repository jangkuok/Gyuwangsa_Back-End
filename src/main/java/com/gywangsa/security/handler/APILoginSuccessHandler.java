package com.gywangsa.security.handler;

import com.google.gson.Gson;
import com.gywangsa.domain.Member;
import com.gywangsa.dto.MemberAuthorityDTO;
import com.gywangsa.repository.MemberRepository;
import com.gywangsa.service.MemberService;
import com.gywangsa.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("-----------------------APILoginSuccessHandler-----------------------");
        log.info(authentication);
        log.info("-----------------------APILoginSuccessHandler-----------------------");

        MemberAuthorityDTO memberAuthorityDTO = (MemberAuthorityDTO) authentication.getPrincipal();

        Map<String, Object> claims = memberAuthorityDTO.getClaims();

        String accessToken = JWTUtil.generateToken(claims, 10);
        String refreshToken = JWTUtil.generateToken(claims, 60*24);

        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(claims);

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();
    }
}
