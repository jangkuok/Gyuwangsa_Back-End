package com.gywangsa.controller;

import com.gywangsa.dto.BrandDTO;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.service.BrandService;
import com.gywangsa.util.CustomFileUtil;
import com.gywangsa.util.CustomFileUtilBrand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    private final CustomFileUtilBrand customFileUtilBrand;

    //브랜드 조회
    @GetMapping("/info/{brandNo}")
    public BrandDTO selectBrandByBrandNo(@PathVariable("brandNo") Long brandNo){
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 조회============");

        BrandDTO brandDTO = brandService.selectBrandByBrandNo(brandNo);
        log.info(brandDTO);

        return brandDTO;
    }

    //브랜드 리스트 조회
    @GetMapping("/list")
    public PageResponseDTO<BrandDTO> selectBrandList(PageRequestDTO pageRequestDTO){
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 리스트 조회============");
        return brandService.selectBrandList(pageRequestDTO);
    }

    //브랜드 등록
    @PostMapping("/addBrand")
    public Map<String, Long> insertBrand(@RequestPart(value = "brandDTO") BrandDTO brandDTO,
                                         @RequestPart(value = "logFile",required = false) MultipartFile file){
        log.info("-------------------BrandController-------------------");
        log.info("============브랜드 등록============");

        String fileName = customFileUtilBrand.saveBrandFile(file);
        brandDTO.setBrandLog(fileName);

        LocalDateTime now = LocalDateTime.now();
        brandDTO.setStartDate(now);
        brandDTO.setEndDate(now.plusMonths(24));

        Long brandNo = brandService.insertBrand(brandDTO);

        return Map.of("brandNo",brandNo);
    }
}
