package com.gywangsa.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdSizeDTO {
    private String sizeType; //사이즈 타입
    private String pdType; //상품 타입
    private String attr1; //속성1
    private String attr2; //속성2
    private String attr3; //속성3
    private String attr4; //속성4
    private String attr5; //속성5
    private String attr6; //속성6
    private String attr7; //속성7
    private String color; //색상
    private String colorCode; //컬러코드
    private int sizeCnt; //수량
}
