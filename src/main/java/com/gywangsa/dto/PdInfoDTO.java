package com.gywangsa.dto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdInfoDTO {

    private long categoryNo; //카테고리 번호
    private long itemNo; //중분류 번호
    private long pdNo; //상품 번호
    private long brandNo; //브랜드 번호
    private LocalDateTime startDate; //유효시작일
    private String pdName; //상품이름
    private LocalDateTime endDate; //유효종료일
    private int buyAmt; //가격
    private int likeCnt; //좋아요 갯수
    private String pdImage; //상품 이미지
    private String sexCd; //성별
    private String note; //비고
    private boolean delFlag;//상품 삭제

    @Builder.Default
    private  List<MultipartFile> files = new ArrayList<>();
    @Builder.Default
    private List<String> imageList = new ArrayList<>();


}
