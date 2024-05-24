package com.gywangsa.service;

import com.gywangsa.domain.PdItem;
import com.gywangsa.dto.PdItemDTO;

import java.util.List;

public interface PdItemService {
    List<PdItemDTO> selectListItem(Long categoryNo);

    default PdItemDTO entityPdItem(PdItem pdItem){
        PdItemDTO pdItemDTO = PdItemDTO.builder()
                .itemNo(pdItem.getItemNo())
                .itemNm(pdItem.getItemNm())
                .build();
        return pdItemDTO;
    }

    default PdItem dtoPdItem(PdItemDTO pdItemDTO){
        PdItem pdItem = PdItem.builder()
                .itemNo(pdItemDTO.getItemNo())
                .itemNm(pdItemDTO.getItemNm())
                .build();
        return pdItem;
    }
}
