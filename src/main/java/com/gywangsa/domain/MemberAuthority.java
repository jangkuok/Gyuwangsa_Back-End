package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GYU_SYS_ROLE_USER")
public class MemberAuthority {
    @Id
    @GeneratedValue
    @Column(name = "user_role_no")
    private Long uaNo;
    @Column(name = "role_nm")
    private String roleNm;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    @Column(name = "brand_cd")
    private Long brandCd;
    @Column(name = "brand_nm")
    private String brandNm;
    @Column(name = "note")
    private String note;

    public void changePwd(String pwd) {
        this.pwd = pwd;
    }

    public void changeStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void changeEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void changeBrandCd(Long brandCd) {
        this.brandCd = brandCd;
    }

    public void changeBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }

    public void changeNote(String note) {
        this.note = note;
    }
}