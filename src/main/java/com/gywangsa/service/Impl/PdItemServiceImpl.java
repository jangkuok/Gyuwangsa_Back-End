package com.gywangsa.service.Impl;

import com.gywangsa.domain.PdItem;
import com.gywangsa.dto.PdItemDTO;
import com.gywangsa.repository.PdItemRepository;
import com.gywangsa.service.PdItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PdItemServiceImpl implements PdItemService {

    private final PdItemRepository pdItemRepository;

    @Override
    public List<PdItemDTO> selectListItem(Long categoryNo) {

        List<PdItem> pdItemList = pdItemRepository.selectListItem(categoryNo);

        List<PdItemDTO> dtoList = pdItemList.stream().map(m ->
                entityPdItem(m)).collect(Collectors.toList());

        return dtoList;
    }
}
