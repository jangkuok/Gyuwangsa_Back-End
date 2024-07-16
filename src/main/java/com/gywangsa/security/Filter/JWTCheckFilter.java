package com.gywangsa.security.Filter;

import com.google.gson.Gson;
import com.gywangsa.dto.MemberAuthorityDTO;
import com.gywangsa.dto.MemberDTO;
import com.gywangsa.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        //true => 체크안함
        //false => 체크함

        String path = request.getRequestURI();

        log.info("-----------shouldNotFilter---------------");
        log.info("check uri----------" + path);
        log.info("-----------------------------------------");

            if(path.startsWith("/user/") || path.startsWith("/category/") || path.startsWith("/product/item/") || path.startsWith("/product/info/")
                    || path.startsWith("/product/view/") || path.startsWith("/product/search/")  || path.startsWith("/brand/")
                    || path.startsWith("/like/") || path.startsWith("/color/")
                   ){
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("------------------------JWTCheckFilter-----------------------");
        log.info("-------------------------------------------------------------");

        String authHeaderStr = request.getHeader("Authorization");

        //Bearer //7 JWT 문자열
        try {
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            log.info("JWT claims: " + claims);

            String roleNm = String.valueOf(claims.get("roleNm"));
            String pwd = String.valueOf(claims.get("pwd"));
            String userId = String.valueOf(claims.get("userId"));
            Long brandCd = Long.valueOf(String.valueOf(claims.get("brandCd")));
            String brandNm = String.valueOf(claims.get("brandNm"));
            String note = String.valueOf(claims.get("note"));

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+roleNm));

            MemberAuthorityDTO memberAuthorityDTO = new MemberAuthorityDTO(pwd, authorities, roleNm, userId, brandCd, brandNm, note);

            log.info("-----------------UsernamePasswordAuthenticationToken------------------");
            log.info(memberAuthorityDTO);
            log.info(memberAuthorityDTO.getAuthorities());

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(memberAuthorityDTO, pwd, memberAuthorityDTO.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //성공하면 다음 페이지 이동
            filterChain.doFilter(request, response);

        }catch(Exception e){

            log.error("JWT Check Error..............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }

    }

}
