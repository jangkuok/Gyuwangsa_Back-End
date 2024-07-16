package com.gywangsa.service.Impl;

import com.gywangsa.domain.*;
import com.gywangsa.dto.PdCategoryDTO;
import com.gywangsa.dto.PdItemDTO;
import com.gywangsa.repository.PdCategoryRepository;
import com.gywangsa.service.PdCategoryService;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PdCategoryServiceImpl implements PdCategoryService {

    private final PdCategoryRepository categoryRepository;


    //카테고리/아이템 조회
    @Override
    public List<PdCategoryDTO> selectListCategory() {

        List<PdCategory> result = categoryRepository.selectCategoryItem();

        List<PdCategoryDTO> categoryDTOList = result.stream().map(list -> entityCategory(list)).collect(Collectors.toList());

        return categoryDTOList;
    }
    
    //카테고리 조회
    @Override
    public List<PdCategoryDTO> selectCategory() {
        List<PdCategory> result = categoryRepository.selectCategory();

        List<PdCategoryDTO> categoryDTOList = result.stream().map(list -> entityCategory(list)).collect(Collectors.toList());

        return categoryDTOList;
    }

}
