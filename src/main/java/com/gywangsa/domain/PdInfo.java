package com.gywangsa.domain;

import com.gywangsa.pk.PdInfoPk;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GYU_PD_INFO")
@IdClass(PdInfoPk.class)
public class PdInfo {
    @Id
    @Column(name = "category_no")
    private long categoryNo; //카테고리 번호

    @Id
    @Column(name = "item_no")
    private long itemNo; //중분류 번호

    @Id
    @Column(name = "pd_no")
    private long pdNo; //상품 번호

    @Id
    @Column(name = "brand_no")
    private long brandNo; //브랜드 번호

    @Id
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate startDate; //유효시작일

    @Column(name = "pd_name", length = 300)
    private String pdName; //상품이름

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate endDate; //유효종료일

    @Column(name = "buy_amt")
    private int buyAmt; //가격

    @Column(name = "like_cnt")
    private int likeCnt; //좋아요 갯수

    @Column(name = "pd_image", length = 500)
    private String pdImage; //상품 이미지

    @Column(name = "sex_cd", length = 10)
    private String sexCd; //성별

    @Column(name = "note", length = 300, nullable = true)
    private String note; //비고

}
