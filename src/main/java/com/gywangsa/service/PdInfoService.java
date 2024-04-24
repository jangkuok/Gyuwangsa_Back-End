package com.gywangsa.service;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PdInfoDTO;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@Transactional
public interface PdInfoService {
    PdInfoDTO getPdInfo(Long categoryNo, Long itemNo, Long pdNo, Long brandNo, LocalDate startDate);

    default PdInfoDTO entityPdInfo(PdInfo pdInfo){
        PdInfoDTO pdInfoDTO = PdInfoDTO.builder()
                .categoryNo(pdInfo.getCategoryNo())
                .itemNo(pdInfo.getItemNo())
                .brandNo(pdInfo.getBrandNo())
                .pdNo(pdInfo.getPdNo())
                .startDate(pdInfo.getStartDate())
                .pdName(pdInfo.getPdName())
                .endDate(pdInfo.getEndDate())
                .buyAmt(pdInfo.getBuyAmt())
                .likeCnt(pdInfo.getLikeCnt())
                .pdImage(pdInfo.getPdImage())
                .sexCd(pdInfo.getSexCd())
                .note(pdInfo.getNote())
                .build();

        return pdInfoDTO;
    }

    default PdInfo dtoPdInfo(PdInfoDTO pdInfoDTO){
        PdInfo pdInfo = PdInfo.builder()
                .categoryNo(pdInfoDTO.getCategoryNo())
                .itemNo(pdInfoDTO.getItemNo())
                .brandNo(pdInfoDTO.getBrandNo())
                .pdNo(pdInfoDTO.getPdNo())
                .startDate(pdInfoDTO.getStartDate())
                .pdName(pdInfoDTO.getPdName())
                .endDate(pdInfoDTO.getEndDate())
                .buyAmt(pdInfoDTO.getBuyAmt())
                .likeCnt(pdInfoDTO.getLikeCnt())
                .pdImage(pdInfoDTO.getPdImage())
                .sexCd(pdInfoDTO.getSexCd())
                .note(pdInfoDTO.getNote())
                .build();

        return pdInfo;
    }


}
