package com.gywangsa.service;

import com.gywangsa.domain.PdCategory;
import com.gywangsa.domain.PdItem;
import com.gywangsa.dto.PdCategoryDTO;

import com.gywangsa.dto.PdItemDTO;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public interface PdCategoryService {

    //조회
    List<PdCategoryDTO> selectListCategory();

    default PdCategoryDTO entityCategory(PdCategory category){

        List<PdItemDTO> dtoList = new ArrayList<>();

        category.getPdItemList().stream().map(m -> {PdItem item = PdItem.builder()
                .itemNo(m.getItemNo())
                .itemNm(m.getItemNm())
                .build();
                dtoList.add(item.addPdItemDTO(m));
                return item;
        }).collect(Collectors.toList());



        System.out.println("+================");



        PdCategoryDTO categoryDTO = PdCategoryDTO.builder()
                .categoryNo(category.getCategoryNo())
                .categoryNm(category.getCategoryNm())
                .pdItemList(dtoList)
                .build();
        return categoryDTO;
    }

    default PdCategory dtoCategory(PdCategoryDTO categoryDTO){
        PdCategory category = PdCategory.builder()
                .categoryNo(categoryDTO.getCategoryNo())
                .categoryNm(categoryDTO.getCategoryNm())
                .pdItemList(categoryDTO.getPdItemList().stream().map(en -> new PdItem()).collect(Collectors.toList()))
                .build();
        return category;
    }
}
