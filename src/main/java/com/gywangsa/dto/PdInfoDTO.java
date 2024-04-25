package com.gywangsa.dto;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdInfoDTO {

    private long categoryNo; //카테고리 번호
    private long itemNo; //중분류 번호
    private long pdNo; //상품 번호
    private long brandNo; //브랜드 번호
    private LocalDate startDate; //유효시작일
    private String pdName; //상품이름
    private LocalDate endDate; //유효종료일
    private int buyAmt; //가격
    private int likeCnt; //좋아요 갯수
    private String pdImage; //상품 이미지
    private String sexCd; //성별
    private String note; //비고
}
