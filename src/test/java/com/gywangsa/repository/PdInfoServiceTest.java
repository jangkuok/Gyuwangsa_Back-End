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
import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class PdInfoServiceTest {

    @Autowired
    PdInfoService pdInfoService;


    @Test
    public void insertPdInfo(){
        for(int i =1; i<=1;i++){
            PdInfoDTO dto = PdInfoDTO.builder()
                    .startDate(LocalDateTime.now())
                    .pdName("티셔츠")
                    .endDate(LocalDateTime.now())
                    .buyAmt(2500)
                    .likeCnt(6)
                    .pdImage("이미지")
                    .sexCd("남")
                    .note("")
                    .build();
            log.info(pdInfoService.insertPdInfo(dto));
        }
    }

    @Test
    public void selectPdInfoByPdNo(){
        Long no = 2L;
        log.info(pdInfoService.selectPdInfoByPdNo(no));
    }

    @Test
    public void selectListByPdInfo(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(2).build();

        log.info(pdInfoService.selectListByPdInfo(pageRequestDTO));
    }

}
