package com.gywangsa.repository;

import com.gywangsa.domain.PdInfo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class PdInfoRepositoryTest {


    @Autowired
    private PdInfoRepository pdInfoRepository;

    @Test
    public void test1(){
        Assertions.assertNotNull(pdInfoRepository);
        log.info(pdInfoRepository.getClass().getName());
    }
    @Test
    public void testInsert(){

        for(int i = 1; i< 20; i++){
            PdInfo pdInfo = PdInfo.builder()
                    .categoryNo(1 + i)
                    .itemNo(1 + i)
                    .brandNo(1 + i)
                    .pdNo(1 + i)
                    .startDate(LocalDateTime.now())
                    .pdName("가성비 티셔츠" + i)
                    .endDate(LocalDateTime.now())
                    .buyAmt(12000)
                    .likeCnt(3)
                    .pdImage("이미지")
                    .sexCd("남")
                    .note("")
                    .build();
            PdInfo result = pdInfoRepository.save(pdInfo);

            log.info(result);
        }



    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("pdNo").descending());
        Page<PdInfo> list = pdInfoRepository.findAll(pageable);
        log.info(list.getTotalElements());
        log.info(list.getContent());
    }

    @Test
    public void selectPdInfoByProduct(){
        Long pdNo = 2L;
        log.info(pdInfoRepository.selectPdInfoByPdNo(pdNo));
    }
/*
    @Test
    public void testSearch(){

        pdInfoRepository.search();
    }
*/

}
