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

    @Column(name = "eng_nm")
    private String engNm;

    @Column(name = "brand_log")
    private String brandLog;

    @Column(name = "brand_main_image")
    private String brandMainImage;

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

    public void changeBrandNo(Long brandNo) {
        this.brandNo = brandNo;
    }

    public void changeBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }

    public void changeEngNm(String engNm) {
        this.engNm = engNm;
    }

    public void changeBrandLog(String brandLog) {
        this.brandLog = brandLog;
    }

    public void changeBrandMainImage(String brandMainImage) {
        this.brandMainImage = brandMainImage;
    }

    public void changeAddrNo(String addrNo) {
        this.addrNo = addrNo;
    }

    public void changeAddr(String addr) {
        this.addr = addr;
    }

    public void changeAddrDtl(String addrDtl) {
        this.addrDtl = addrDtl;
    }

    public void changeComCall(String comCall) {
        this.comCall = comCall;
    }

    public void changeComEmail(String comEmail) {
        this.comEmail = comEmail;
    }

    public void changeDeliComp(String deliComp) {
        this.deliComp = deliComp;
    }

    public void changeStateCd(boolean stateCd) {
        this.stateCd = stateCd;
    }

    public void changeStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void changeEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void changeNote(String note) {
        this.note = note;
    }
}
