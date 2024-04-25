package com.gywangsa.pk;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class PdInfoPk implements Serializable {
    private long categoryNo; //카테고리 번호
    private long itemNo; //중분류 번호
    private long pdNo; //상품 번호
    private long brandNo; //브랜드 번호
    private LocalDate startDate; //유효시작일

}
