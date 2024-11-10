package com.gywangsa.dto;

import com.gywangsa.domain.PdCategory;
import com.gywangsa.domain.PdItem;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdItemDTO {

    private int itemNo;//아이템 번호

    private String itemNm;//아이템 이름

    private PdCategory pdCategory;

}
