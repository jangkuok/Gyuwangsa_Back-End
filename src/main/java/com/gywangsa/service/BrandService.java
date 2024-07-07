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

    Long insertBrand(BrandDTO brandDTO);

    PageResponseDTO<BrandDTO> selectBrandList(PageRequestDTO pageRequestDTO);

    BrandDTO selectBrandByBrandNo(Long brandNo);


    default BrandDTO entityBrand(Brand brand){
        BrandDTO brandDTO = BrandDTO.builder()
                .brandNo(brand.getBrandNo())
                .brandNm(brand.getBrandNm())
                .brandLog(brand.getBrandLog())
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
                .brandLog(brandDTO.getBrandLog())
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
