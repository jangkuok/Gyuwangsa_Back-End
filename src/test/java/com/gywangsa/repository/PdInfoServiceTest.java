package com.gywangsa.repository;

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
    public void getPdInfo(){
        Long no = 2L;
        log.info(pdInfoService.getPdInfo(no,no,no,no,LocalDate.parse("2024-04-24")));
    }
}
