package com.gywangsa.service;
import com.gywangsa.domain.PdFile;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface PdInfoService {

    //상풍 등록
    Long insertPdInfo(PdInfoDTO dto);

    //상품 수정
    void modifyPdInfoByPdNo(PdInfoDTO pdInfoDTO);

    //상품 삭제
    void removePdInfoByPdNo(Long pdNo);

    //상품 리스트 조회
    PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO,Long categoryNo, Long itemNo);

    //특정 상품 조회
    //PdInfoDTO selectPdInfoByPdNo(Long brandNo, Long categoryNo, Long itemNo, Long pdNo);
    PdInfoDTO selectPdInfoByPdNo(Long pdNo);

    //Entity -> DTO
    default PdInfoDTO entityPdInfo(PdInfo pdInfo){
        PdInfoDTO pdInfoDTO = PdInfoDTO.builder()
                .categoryNo(pdInfo.getCategoryNo())
                .itemNo(pdInfo.getItemNo())
                .brandNo(pdInfo.getBrandNo())
                .brandNm(pdInfo.getBrandNm())
                .pdNo(pdInfo.getPdNo())
                .startDate(pdInfo.getStartDate())
                .pdName(pdInfo.getPdName())
                .endDate(pdInfo.getEndDate())
                .buyAmt(pdInfo.getBuyAmt())
                .likeCnt(pdInfo.getLikeCnt())
                .sexCd(pdInfo.getSexCd())
                .note(pdInfo.getNote())
                .build();

        List<PdFile> imageList = pdInfo.getFileList();

        if(imageList == null || imageList.size() == 0){
            return pdInfoDTO;
        }

        List<String> fileList = imageList.stream().map(m -> m.getFileNm()).toList();
        pdInfoDTO.setImageList(fileList);

        return pdInfoDTO;
    }

    //DTO -> Entity
    default PdInfo dtoPdInfo(PdInfoDTO pdInfoDTO){
        PdInfo pdInfo = PdInfo.builder()
                .categoryNo(pdInfoDTO.getCategoryNo())
                .itemNo(pdInfoDTO.getItemNo())
                .brandNo(pdInfoDTO.getBrandNo())
                .brandNm(pdInfoDTO.getBrandNm())
                .pdNo(pdInfoDTO.getPdNo())
                .startDate(pdInfoDTO.getStartDate())
                .pdName(pdInfoDTO.getPdName())
                .endDate(pdInfoDTO.getEndDate())
                .buyAmt(pdInfoDTO.getBuyAmt())
                .likeCnt(pdInfoDTO.getLikeCnt())
                .sexCd(pdInfoDTO.getSexCd())
                .note(pdInfoDTO.getNote())
                .build();

        List<String> imageList = pdInfoDTO.getImageList();

        if(imageList == null || imageList.size() == 0){
            return pdInfo;
        }

        imageList.forEach(fileName -> {
            pdInfo.addFileString(fileName);
        });

        return pdInfo;
    }


}
