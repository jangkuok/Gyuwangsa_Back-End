package com.gywangsa.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {


    private String userId;
    private String pwd;
    private String name;
    private String email;
    private String phone;
    private String addrNo;
    private String addr;
    private String addrDtl;
    private String sexCd;
    private char pwdYn;
    private LocalDateTime pwdDate;
    private LocalDateTime loginDate;
    private String joinType;
    private String apiKey;


}
