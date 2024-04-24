package com.gywangsa.service.Impl;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.pk.PdInfoPk;
import com.gywangsa.repository.PdInfoRepository;
import com.gywangsa.service.PdInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PdInfoServiceImpl implements PdInfoService {

    private final PdInfoRepository pdInfoRepository;

    @Override
    public PdInfoDTO getPdInfo(Long categoryNo, Long itemNo, Long pdNo, Long brandNo, LocalDate startDate) {

        PdInfoPk pdInfoPk = new PdInfoPk();
        pdInfoPk.setCategoryNo(categoryNo);
        pdInfoPk.setItemNo(itemNo);
        pdInfoPk.setPdNo(pdNo);
        pdInfoPk.setBrandNo(brandNo);
        pdInfoPk.setStartDate(startDate);

        log.info(pdInfoPk);

        Optional<PdInfo> result = pdInfoRepository.findById(pdInfoPk);
        PdInfo pdInfo = result.orElseThrow();
        return entityPdInfo(pdInfo);
    }
}
