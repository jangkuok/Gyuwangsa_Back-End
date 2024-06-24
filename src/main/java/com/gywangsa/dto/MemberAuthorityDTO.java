package com.gywangsa.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberAuthorityDTO extends User {

    private Long uaNo;
    private String roleNm;
    private String userId;
    private String pwd;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long brandCd;
    private String brandNm;
    private String note;


    public MemberAuthorityDTO(String pwd, List<GrantedAuthority> authorities,String roleNm, String userId,
                              Long brandCd, String brandNm, String note) {
        super(userId, pwd, authorities);
        this.roleNm = roleNm;
        this.pwd = pwd;
        this.userId = userId;
        this.brandCd = brandCd;
        this.brandNm = brandNm;
        this.note = note;
    }

    //jwt
    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("roleNm", roleNm);
        dataMap.put("userId", userId);
        dataMap.put("pwd", pwd);
        dataMap.put("brandNm", brandNm);
        dataMap.put("brandCd", brandCd);
        dataMap.put("note", note);

        return dataMap;
    }
}

