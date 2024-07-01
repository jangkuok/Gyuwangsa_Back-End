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
    @Column(name = "brand_cd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandCd;

    @Column(name = "brand_nm")
    private String brandNm;

    @Column(name = "brand_log")
    private String brandLog;

    @Column(name = "add_no")
    private String addNo;

    @Column(name = "add")
    private String add;

    @Column(name = "add_dtl")
    private String addDtl;

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
