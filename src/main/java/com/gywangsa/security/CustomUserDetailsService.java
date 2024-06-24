package com.gywangsa.security;

import com.gywangsa.domain.Member;
import com.gywangsa.domain.MemberAuthority;
import com.gywangsa.dto.MemberAuthorityDTO;
import com.gywangsa.dto.MemberDTO;
import com.gywangsa.repository.MemberAuthorityRepository;
import com.gywangsa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberAuthorityRepository memberAuthorityRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("---------------------------------------------");
        log.info("-----------CustomUserDetailsService----------" + username);

        //Member member = memberRepository.selectMemberId(username);
        MemberAuthority ma = memberAuthorityRepository.selectUserAuthority(username);
        log.info(ma);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ma.getRoleNm()));

        if(ma == null){
            throw new UsernameNotFoundException("Not found");
        }

        MemberAuthorityDTO memberAuthorityDTO = new MemberAuthorityDTO(
                ma.getPwd(),
                authorities,
                ma.getRoleNm(),
                ma.getUserId(),
                ma.getBrandCd(),
                ma.getBrandNm(),
                ma.getNote()
        );

        log.info(memberAuthorityDTO);

        return memberAuthorityDTO;
    }
}
