package com.gywangsa.dto;

import com.gywangsa.domain.PdCategory;
import com.gywangsa.domain.PdItem;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdCategoryDTO {

    private int categoryNo;//카테고리 번호
    private String categoryNm; //카테고리 이름
    private List<PdItemDTO> pdItemList = new ArrayList<>();
}
