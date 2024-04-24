package com.gywangsa.pk;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Data
public class PdInfoPk implements Serializable {
    private int category_no; //카테고리 번호
    private int item_no; //중분류 번호
    private int pd_no; //상품 번호
    private int brand_no; //브랜드 번호
    private LocalDate start_date; //유효시작일

}
