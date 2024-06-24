package com.gywangsa.service.Impl;

import com.gywangsa.domain.MemberAuthority;
import com.gywangsa.dto.MemberAuthorityDTO;
import com.gywangsa.repository.MemberAuthorityRepository;
import com.gywangsa.service.MemberAuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberAuthorityServiceImpl implements MemberAuthorityService {

    private final MemberAuthorityRepository memberAuthorityRepository;

    //유저 권한 검색
    @Override
    public MemberAuthorityDTO selectByMemberAuthority(String userId) {

        MemberAuthority memberAuthority =  memberAuthorityRepository.selectUserAuthority(userId);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+memberAuthority.getRoleNm()));

        MemberAuthorityDTO dto = new MemberAuthorityDTO(
                memberAuthority.getPwd(),
                authorities,
                memberAuthority.getRoleNm(),
                memberAuthority.getUserId(),
                memberAuthority.getBrandCd(),
                memberAuthority.getBrandNm(),
                memberAuthority.getNote()
        );

        return dto;
    }

}
