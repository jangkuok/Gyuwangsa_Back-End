package com.gywangsa.repository;

import com.gywangsa.domain.LikeChk;
import com.gywangsa.domain.PdInfo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Log4j2
public class LikeChkRepositoryTest {

    @Autowired
    private LikeChkRepository likeChkRepository;

    @Autowired
    private PdInfoRepository pdInfoRepository;

    @Test
    public void insertLike() {
        String userId = "test1";
        Long pdNo = 227L;

        LikeChk likeChk = LikeChk.builder()
                .pdNo(pdNo)
                .userId(userId)
                .build();

        likeChkRepository.save(likeChk);
    }

    @Test
    public void selectUserIdLikeChk() {
        List<LikeChk> likeChkList = likeChkRepository.selectUserIdLikeChk("test1");

        List<PdInfo> pdInfoList = pdInfoRepository.findAll();

        int cnt = 1;

        for (int i = 0; i < likeChkList.size(); i++) {
            for (int j = 0; j < pdInfoList.size(); j++) {
                if (likeChkList.get(i).getPdNo().equals(pdInfoList.get(j).getPdNo())) {
                    //log.info(cnt);
                    //cnt ++;
                    log.info("안녕");
                }
            }
        }
    }


}
