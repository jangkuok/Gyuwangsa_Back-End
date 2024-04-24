package com.gywangsa.domain;

import com.gywangsa.pk.PdInfoPk;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GYU_PD_INFO")
@IdClass(PdInfoPk.class)
public class PdInfo {
    @Id
    private int category_no; //카테고리 번호
    @Id
    private int item_no; //중분류 번호
    @Id
    private int pd_no; //상품 번호
    @Id
    private int brand_no; //브랜드 번호
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate start_date; //유효시작일
    @Column(length = 300)
    private String pd_name; //상품이름
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate end_date; //유효종료일
    private int buy_amt; //가격
    private int like_cnt; //좋아요 갯수
    @Column(length = 500)
    private String pd_image; //상품 이미지
    @Column(length = 10)
    private String sex_cd; //성별
    @Column(length = 300, nullable = true)
    private String note; //비고

}
