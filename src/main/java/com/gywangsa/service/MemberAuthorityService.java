package com.gywangsa.service;

import com.gywangsa.domain.MemberAuthority;
import com.gywangsa.dto.MemberAuthorityDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
public interface MemberAuthorityService {

    MemberAuthorityDTO selectByMemberAuthority(String userId);

}
