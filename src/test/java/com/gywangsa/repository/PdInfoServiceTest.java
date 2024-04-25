package com.gywangsa.repository;

import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.pk.PdInfoPk;
import com.gywangsa.service.PdInfoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class PdInfoServiceTest {

    @Autowired
    PdInfoService pdInfoService;


    @Test
    public void insertPdInfo(){
        PdInfoDTO dto = PdInfoDTO.builder()
                .startDate(LocalDate.now())
                .pdName("데님 팬츠")
                .endDate(LocalDate.now())
                .buyAmt(45000)
                .likeCnt(6)
                .pdImage("이미지")
                .sexCd("여")
                .note("")
                .build();

        log.info(pdInfoService.insertPdInfo(dto));
    }

    @Test
    public void selectPdInfoByPdNo(){
        Long no = 2L;
        log.info(pdInfoService.selectPdInfoByPdNo(no,no,no,no,LocalDate.parse("2024-04-24")));
    }

    @Test
    public void selectListByPdInfo(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(2).build();

        log.info(pdInfoService.selectListByPdInfo(pageRequestDTO));
    }

}
