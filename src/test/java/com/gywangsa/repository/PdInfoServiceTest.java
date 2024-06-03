package com.gywangsa.repository;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.pk.PdInfoPk;
import com.gywangsa.service.PdInfoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class PdInfoServiceTest {

    @Autowired
    PdInfoService pdInfoService;


    @Test
    public void insertPdInfo(){
        for(int i = 0; i<=20; i++){
        PdInfoDTO pdInfoDTO = PdInfoDTO.builder()
                .categoryNo(3L)
                .itemNo(3L)
                .brandNo(2L)
                .brandNm("엘무드")
                .startDate(LocalDateTime.now())
                .pdName("블레이저")
                .endDate(LocalDateTime.now())
                .buyAmt(52000)
                .likeCnt(3)
                .sexCd("남")
                .note("블래이저 입니다.")
                .delFlag(false)
                .build();

        pdInfoDTO.setImageList(java.util.List.of(
                UUID.randomUUID()+"_"+"Image_3.jpg",
                UUID.randomUUID()+"_"+"Image_4.jpg"
        ));
            long result = pdInfoService.insertPdInfo(pdInfoDTO);
        }
    }

    @Transactional
    @Test
    public void selectPdInfoByPdNo(){
        Long no = 148L;
        log.info(pdInfoService.selectPdInfoByPdNo(no));
    }

    @Transactional
    @Test
    public void selectListByPdInfo(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<PdInfoDTO> responseDTO = pdInfoService.selectListByPdInfo(pageRequestDTO,1L,1L);

        log.info(responseDTO.getDtoList().size());
    }

}
