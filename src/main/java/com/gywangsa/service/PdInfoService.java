package com.gywangsa.service;
import com.gywangsa.domain.PdFile;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.domain.PdSize;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.dto.PdSizeDTO;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    //브랜드 리스트 조회
    PageResponseDTO<PdInfoDTO> selectListByBrandPdInfo(PageRequestDTO pageRequestDTO,Long brandNo);

    //브랜드 리스트 카테고리 조회
    PageResponseDTO<PdInfoDTO> selectListByBrandCategory(PageRequestDTO pageRequestDTO, Long categoryNo, Long brandNo);

    //브랜드 리스트 카테고리 아이템 조회
    PageResponseDTO<PdInfoDTO> selectListByBrandCategoryItem(PageRequestDTO pageRequestDTO, Long categoryNo, Long itemNo, Long brandNo);

    //상품 키워드 조회
    PageResponseDTO<PdInfoDTO> selectBrandByKeyword(PageRequestDTO pageRequestDTO, String keyword);

    //특정 상품 조회
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

        //이미지
        List<PdFile> imageList = pdInfo.getFileList();

        if(imageList == null || imageList.size() == 0){
            return pdInfoDTO;
        }

        List<String> fileList = imageList.stream().map(m -> m.getFileNm()).toList();
        pdInfoDTO.setImageList(fileList);

        //사이즈
        List<PdSize> pdSizeList = pdInfo.getSizeList();

        List<PdSizeDTO> sizeList = pdSizeList.stream().map(m -> PdSizeDTO.builder()
                .sizeType(m.getSizeType())
                .pdType(m.getPdType())
                .attr1(m.getAttr1())
                .attr2(m.getAttr2())
                .attr3(m.getAttr3())
                .attr4(m.getAttr4())
                .attr5(m.getAttr5())
                .attr6(m.getAttr6())
                .attr7(m.getAttr7())
                .color(m.getColor())
                .colorCode(m.getColorCode())
                .sizeCnt(m.getSizeCnt())
                .build()).collect(Collectors.toList());

        pdInfoDTO.setSizeList(sizeList);

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

        //이미지
        List<String> imageList = pdInfoDTO.getImageList();

        if(imageList == null || imageList.size() == 0){
            return pdInfo;
        }

        imageList.forEach(fileName -> {
            pdInfo.addFileString(fileName);
        });

        //사이즈
        List<PdSizeDTO> pdSizeDTOList = pdInfoDTO.getSizeList();

        List<PdSize> pdSizeList = pdSizeDTOList.stream().map(m -> PdSize.builder()
                .sizeType(m.getSizeType())
                .pdType(m.getPdType())
                .attr1(m.getAttr1())
                .attr2(m.getAttr2())
                .attr3(m.getAttr3())
                .attr4(m.getAttr4())
                .attr5(m.getAttr5())
                .attr6(m.getAttr6())
                .attr7(m.getAttr7())
                .color(m.getColor())
                .colorCode(m.getColorCode())
                .sizeCnt(m.getSizeCnt())
                .build()).collect(Collectors.toList());

        pdInfo.changeSizeList(pdSizeList);

        return pdInfo;
    }


}
