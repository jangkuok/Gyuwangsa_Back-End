package com.gywangsa.controller;

import com.gywangsa.dto.LikeChkDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.service.LikeChkService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeChkController {

    private final LikeChkService likeChkService;

    //좋아요 리스트
    @GetMapping("/{userId}")
    public List<LikeChkDTO> selectUserIdLikeChk(@PathVariable("userId") String userId){
        log.info("-------------------LikeChkController-------------------");
        log.info("============좋아요 리스트============");

        return likeChkService.selectUserIdLikeChk(userId);
    }

    //좋아요 리스트2
    @GetMapping("/list/{userId}")
    public List<PdInfoDTO> selectUserIdLikePdInfo(@PathVariable("userId") String userId){
        log.info("-------------------LikeChkController-------------------");
        log.info("============좋아요 리스트============");

        return likeChkService.selectUserIdLikePdInfo(userId);
    }

    //좋아요 등록
    @GetMapping("/pick/{userId}/{pdNo}")
    public void insertPdLike(@PathVariable("userId") String userId, @PathVariable("pdNo") Long pdNo){
        log.info("-------------------LikeChkController-------------------");
        log.info("============좋아요============");

        likeChkService.insertPdLike(userId,pdNo);
    }

    //좋아요 삭제
    @GetMapping("/cancel/{userId}/{pdNo}")
    public void removePdLike(@PathVariable("userId") String userId, @PathVariable("pdNo") Long pdNo){
        log.info("-------------------LikeChkController-------------------");
        log.info("============좋아요============");

        likeChkService.removePdLike(userId,pdNo);
    }
}
