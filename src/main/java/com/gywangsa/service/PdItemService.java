package com.gywangsa.service;

import com.gywangsa.domain.PdCategory;
import com.gywangsa.domain.PdItem;
import com.gywangsa.dto.PdItemDTO;

import java.util.List;

public interface PdItemService {
    List<PdItemDTO> selectListItem(Long categoryNo);

    //아이템 등록
    void insertItem(PdItemDTO pdItem,int categoryNo);




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
                .pdCategory(pdItemDTO.getPdCategory())
                .build();
        return pdItem;
    }
}
