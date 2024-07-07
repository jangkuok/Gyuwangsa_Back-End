package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "GYU_SYS_BRAND")
public class Brand {

    @Id
    @Column(name = "brand_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandNo;

    @Column(name = "brand_nm")
    private String brandNm;

    @Column(name = "brand_log")
    private String brandLog;

    @Column(name = "addr_no")
    private String addrNo;

    @Column(name = "addr")
    private String addr;

    @Column(name = "addr_dtl")
    private String addrDtl;

    @Column(name = "com_call")
    private String comCall;

    @Column(name = "com_email")
    private String comEmail;

    @Column(name = "deli_comp")
    private String deliComp;

    @Column(name = "state_cd")
    private boolean stateCd ;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Column(name = "note")
    private String note;

}
