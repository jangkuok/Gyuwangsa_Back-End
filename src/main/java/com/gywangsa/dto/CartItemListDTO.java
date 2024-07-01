package com.gywangsa.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CartItemListDTO {

    private Long cartItemNo;
    private int count;

    private Long brandNo;
    private String brandNm;

    private String size;
    private String color;

    private Long pdNo;
    private String pdName;
    private int buyAmt;
    private String imageFile;


    public CartItemListDTO(Long cartItemNo, int count, Long brandNo, String brandNm, String size, String color, Long pdNo, String pdName, int buyAmt, String imageFile) {
        this.cartItemNo = cartItemNo;
        this.count = count;
        this.brandNo = brandNo;
        this.brandNm = brandNm;
        this.size = size;
        this.color = color;
        this.pdNo = pdNo;
        this.pdName = pdName;
        this.buyAmt = buyAmt;
        this.imageFile = imageFile;
    }
}
