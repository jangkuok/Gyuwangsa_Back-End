package com.gywangsa.repository;

import com.gywangsa.domain.PdInfo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
@Log4j2
public class PdInfoRepositoryTest {

    @Autowired
    private GyuPdInfoRepository gyuPdInfoRepository;

    @Test
    public void test1(){
        Assertions.assertNotNull(gyuPdInfoRepository);
        log.info(gyuPdInfoRepository.getClass().getName());
    }
    @Test
    public void testInsert(){
        PdInfo pdInfo = PdInfo.builder()
                .category_no(1)
                .item_no(1)
                .brand_no(1)
                .pd_no(1)
                .start_date(LocalDate.now())
                .pd_name("가성비 티셔츠")
                .end_date(LocalDate.now())
                .buy_amt(12000)
                .like_cnt(3)
                .pd_image("이미지")
                .sex_cd("남")
                .note("")
                .build();
        PdInfo result = gyuPdInfoRepository.save(pdInfo);

        log.info(result);

    }

}
