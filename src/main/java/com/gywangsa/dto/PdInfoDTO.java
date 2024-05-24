package com.gywangsa.dto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
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

    private Long categoryNo; //카테고리 번호

    private Long itemNo; //중분류 번호

    private Long pdNo; //상품 번호

    private Long brandNo; //브랜드 번호

    private LocalDateTime startDate; //유효시작일

    private String pdName; //상품 이름

    private String brandNm; //브랜드 이름

    private LocalDateTime endDate; //유효종료일

    private int buyAmt; //가격

    private int likeCnt; //좋아요 갯수

    private String sexCd; //성별

    private String note; //설명

    private boolean delFlag; //삭제 상태

    @Builder.Default
    private  List<MultipartFile> files = new ArrayList<>();
    @Builder.Default
    private List<String> imageList = new ArrayList<>();


}
