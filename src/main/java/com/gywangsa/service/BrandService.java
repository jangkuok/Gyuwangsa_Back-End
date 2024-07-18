package com.gywangsa.service;

import com.gywangsa.domain.Brand;
import com.gywangsa.dto.BrandDTO;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface BrandService {

    //브랜드 등록
    Long insertBrand(BrandDTO brandDTO);

    //브랜드 수정
    Long modifyBrand(BrandDTO brandDTO);

    //브랜드 리스트
    PageResponseDTO<BrandDTO> selectBrandList(PageRequestDTO pageRequestDTO);

    //특정 브런드 조회
    BrandDTO selectBrandByBrandNo(Long brandNo);

    //브랜드 키워드 검색
    PageResponseDTO<BrandDTO> selectBrandByKeyword(PageRequestDTO pageRequestDTO, String keyword);

    //브랜드 랜덤 리스트
    List<BrandDTO> selectRandomBrandList();


    default BrandDTO entityBrand(Brand brand){
        BrandDTO brandDTO = BrandDTO.builder()
                .brandNo(brand.getBrandNo())
                .brandNm(brand.getBrandNm())
                .engNm(brand.getEngNm())
                .brandLog(brand.getBrandLog())
                .brandMainImage(brand.getBrandMainImage())
                .addrNo(brand.getAddrNo())
                .addr(brand.getAddr())
                .addrDtl(brand.getAddrDtl())
                .comCall(brand.getComCall())
                .comEmail(brand.getComEmail())
                .deliComp(brand.getDeliComp())
                .stateCd(brand.isStateCd())
                .startDate(brand.getStartDate())
                .endDate(brand.getEndDate())
                .note(brand.getNote())
                .build();

        return brandDTO;
    }

    default Brand dtoBrand(BrandDTO brandDTO){
        Brand brand = Brand.builder()
                .brandNo(brandDTO.getBrandNo())
                .brandNm(brandDTO.getBrandNm())
                .engNm(brandDTO.getEngNm())
                .brandLog(brandDTO.getBrandLog())
                .brandMainImage(brandDTO.getBrandMainImage())
                .addrNo(brandDTO.getAddrNo())
                .addr(brandDTO.getAddr())
                .addrDtl(brandDTO.getAddrDtl())
                .comCall(brandDTO.getComCall())
                .comEmail(brandDTO.getComEmail())
                .deliComp(brandDTO.getDeliComp())
                .stateCd(brandDTO.isStateCd())
                .startDate(brandDTO.getStartDate())
                .endDate(brandDTO.getEndDate())
                .note(brandDTO.getNote())
                .build();

        return brand;
    }
}
