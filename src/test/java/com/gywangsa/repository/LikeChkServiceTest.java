package com.gywangsa.repository;

import com.gywangsa.service.LikeChkService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class LikeChkServiceTest {

    @Autowired
    private LikeChkService likeChkService;

    @Test
    public void selectTest(){
        log.info( likeChkService.selectUserIdLikePdInfo("test1"));
    }
}
