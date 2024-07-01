package com.gywangsa.pk;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode
//@ToString
//@Data
public class PdInfoPk { //implements Serializable {
    private Long categoryNo; //카테고리 번호
    private Long itemNo; //중분류 번호
    private Long pdNo; //상품 번호
    private Long brandNo; //브랜드 번호
}
