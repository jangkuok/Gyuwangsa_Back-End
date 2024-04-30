package com.gywangsa.service;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Transactional
public interface PdInfoService {

    //상풍 등록
    Long insertPdInfo(PdInfoDTO dto);

    //상품 수정
    void modifyPdInfoByPdNo(PdInfoDTO dto);

    //상품 삭제
    void removePdInfoByPdNo(Long brandNo, Long categoryNo, Long itemNo, Long pdNo);

    //상품 리스트 조회
    PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO);

    //특정 상품 조회
    PdInfoDTO selectPdInfoByPdNo(Long brandNo, Long categoryNo, Long itemNo, Long pdNo);

    //Entity -> DTO
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

    //DTO -> Entity
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
