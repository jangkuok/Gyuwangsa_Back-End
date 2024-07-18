package com.gywangsa.repository;


import com.gywangsa.domain.Brand;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    private LocalDateTime now = LocalDateTime.now();

    @Transactional
    @Commit
    @Test
    public void insertBrand(){
        Brand brand = Brand.builder()
                .brandNm("엘부드")
                .brandLog("")
                .addrNo("13477")
                .addr("경기도 성남시 분당구 판교공원로4길 27")
                .addrDtl("301호")
                .deliComp("로젠택배")
                .stateCd(false)
                .startDate(now)
                .endDate(now.plusMonths(24))
                .note("")
                .build();

        brandRepository.save(brand);
    }

    @Test
    public void testList(){
        log.info(brandRepository.selectRandomBrandList());

    }
}
