package com.gywangsa.repository;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PageRequestDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

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

        for (int i = 0; i<= 10; i++){
            PdInfo pdInfo = PdInfo.builder()
                    .categoryNo(1L)
                    .itemNo(1L)
                    .brandNo(2L)
                    .brandNm("엘무드")
                    .startDate(LocalDateTime.now())
                    .pdName("가성비 티셔츠")
                    .endDate(LocalDateTime.now())
                    .buyAmt(12000)
                    .likeCnt(3)
                    .sexCd("남")
                    .note("가성비 티셔츠입니다.")
                    .delFlag(false)
                    .build();

            pdInfo.addFileString(UUID.randomUUID()+"_"+"Image_1.jpg");
            pdInfo.addFileString(UUID.randomUUID()+"_"+"Image_2.jpg");

            PdInfo result = pdInfoRepository.save(pdInfo);
            log.info(result);
        }


    }

//    @Test
//    public void testPaging(@PageableDefault(size = 10) @SortDefault.SortDefaults({@SortDefault(sort = "categoryNo",direction = Sort.Direction.DESC),
//                           @SortDefault(sort = "itemNo",direction = Sort.Direction.DESC)}) Pageable pageable){
//        Page<Object[]> list = pdInfoRepository.selectListItemPdInfo(pageable);
//
//    }


    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("pdNo").descending());
        Page<Object[]> list = pdInfoRepository.selectListItemPdInfo(pageable,1L,1L);
        list.getContent().forEach(arr -> log.info(Arrays.toString(arr)));

    }
    @Test
    public void selectPdInfoByProduct(){
        Long pdNo = 1L;

        Optional<PdInfo> result =pdInfoRepository.selectPdInfoByPdNo(pdNo);

        PdInfo pdInfo = result.orElseThrow();
        log.info(pdInfo);
        log.info(pdInfo.getFileList());
    }

    @Commit
    @Transactional
    @Test
    public void removePdInfo(){
        Long pdNo = 1L;
        pdInfoRepository.modifyToDelFlag(pdNo, true);

        Optional<PdInfo> result =pdInfoRepository.selectPdInfoByPdNo(pdNo);

        PdInfo pdInfo = result.orElseThrow();
        log.info(pdInfo);
    }

    @Test
    public void modifyPdInfo(){
        Optional<PdInfo> result =pdInfoRepository.selectPdInfoByPdNo(1L);
        PdInfo pdInfo = result.orElseThrow();

        pdInfo.changeBuyAmt(20000);
        pdInfo.delFileList();

        pdInfo.addFileString(UUID.randomUUID()+"_"+"addImage_1.jpg");
        pdInfo.addFileString(UUID.randomUUID()+"_"+"addImage_2.jpg");
        pdInfo.addFileString(UUID.randomUUID()+"_"+"addImage_3.jpg");

        pdInfoRepository.save(pdInfo);

    }

    @Test
    public void search(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        pdInfoRepository.selectListByPdInfo(pageRequestDTO,1L,1L);


    }


}

