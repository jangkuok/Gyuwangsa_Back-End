package com.gywangsa.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;


@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PdColor {
    @Column(name = "coloer_no")
    private Long colorNo;//컬러 색상
    @Column(name = "color_nm")
    private String colorNm;//컬러 이름
    @Column(name = "color_code")
    private String colorCode;//컬러 코드

    public void changeColorNo(Long colorNo) {
        this.colorNo = colorNo;
    }

    public void changeColorNm(String colorNm) {
        this.colorNm = colorNm;
    }

    public void changeColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
