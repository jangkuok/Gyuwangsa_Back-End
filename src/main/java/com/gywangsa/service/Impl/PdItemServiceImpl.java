package com.gywangsa.service.Impl;

import com.gywangsa.domain.PdCategory;
import com.gywangsa.domain.PdItem;
import com.gywangsa.dto.PdCategoryDTO;
import com.gywangsa.dto.PdItemDTO;
import com.gywangsa.repository.PdCategoryRepository;
import com.gywangsa.repository.PdItemRepository;
import com.gywangsa.service.PdItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PdItemServiceImpl implements PdItemService {

    private final PdItemRepository pdItemRepository;
    private final PdCategoryRepository pdCategoryRepository;

    @Override
    public List<PdItemDTO> selectListItem(Long categoryNo) {

        List<PdItem> pdItemList = pdItemRepository.selectListItem(categoryNo);

        List<PdItemDTO> dtoList = pdItemList.stream().map(m ->
                entityPdItem(m)).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public void insertItem(PdItemDTO pdItem,int categoryNo) {
        Optional<PdCategory> result = pdCategoryRepository.findById(Long.valueOf(categoryNo));
        PdCategory pdCategory = result.orElseThrow();

        pdItem.setPdCategory(pdCategory);

        log.info(pdItem);

        pdItemRepository.save(dtoPdItem(pdItem));
    }
}
