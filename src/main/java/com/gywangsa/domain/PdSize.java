package com.gywangsa.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PdSize {
    @Column(name="size_type")
    private String sizeType; //사이즈 타입
    @Column(name="pd_type")
    private String pdType; //상품 타입
    @Column(name="attr1")
    private String attr1; //속성1
    @Column(name="attr2")
    private String attr2; //속성2
    @Column(name="attr3")
    private String attr3; //속성3
    @Column(name="attr4")
    private String attr4; //속성4
    @Column(name="attr5")
    private String attr5; //속성5
    @Column(name="attr6")
    private String attr6; //속성6
    @Column(name="attr7")
    private String attr7; //속성7
    @Column(name="color")
    private String color; //색상
    @Column(name="color_code")
    private String colorCode; //컬러코드
    @Column(name="size_cnt")
    private int sizeCnt; //수량

    public void changeSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public void changePdType(String pdType) {
        this.pdType = pdType;
    }

    public void changeAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public void changeAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public void changeAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public void changeAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public void changeAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public void changeAttr6(String attr6) {
        this.attr6 = attr6;
    }

    public void changeAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public void changeSizeCnt(int sizeCnt) {
        this.sizeCnt = sizeCnt;
    }

    public void changeColor(String color) {
        this.color = color;
    }

    public void changeColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
