package com.gywangsa.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    private String userId;
    private Long pdNo;
    private Long cartItemNo;
    private String size;
    private String color;
    private int count;

}
