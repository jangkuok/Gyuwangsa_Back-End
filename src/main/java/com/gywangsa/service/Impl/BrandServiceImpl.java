package com.gywangsa.service.Impl;

import com.gywangsa.domain.Brand;
import com.gywangsa.dto.BrandDTO;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.repository.BrandRepository;
import com.gywangsa.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Long insertBrand(BrandDTO brandDTO) {

        Brand brand = dtoBrand(brandDTO);

        Brand result = brandRepository.save(brand);

        return result.getBrandNo();
    }

    //브랜드 조회
    @Override
    public PageResponseDTO<BrandDTO> selectBrandList(PageRequestDTO pageRequestDTO) {
        log.info("-------------------BrandServiceImpl-------------------");
        log.info("============브랜드 리스트 조회============");
        //페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("brandNo").descending());

        //JPA
        Page<Brand> result = brandRepository.selectBrandList(pageable);

        List<BrandDTO> dtoList = result.get().map( brand -> entityBrand(brand)).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<BrandDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    @Override
    public BrandDTO selectBrandByBrandNo(Long brandNo) {
        log.info("-------------------BrandServiceImpl-------------------");
        log.info("============브랜드 조회============");
        Brand brand = brandRepository.selectBrandByBrandNo(brandNo);

        if(brand == null){
            brand = new Brand();
        }
        return entityBrand(brand);
    }
}
