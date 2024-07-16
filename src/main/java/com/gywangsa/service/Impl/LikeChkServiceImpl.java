package com.gywangsa.service.Impl;

import com.gywangsa.domain.LikeChk;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.LikeChkDTO;
import com.gywangsa.repository.LikeChkRepository;
import com.gywangsa.repository.PdInfoRepository;
import com.gywangsa.service.LikeChkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class LikeChkServiceImpl implements LikeChkService {

    private final LikeChkRepository likeChkRepository;
    private final PdInfoRepository pdInfoRepository;

    @Override
    public List<LikeChkDTO> selectUserIdLikeChk(String userId) {
        List<LikeChk> likeChkList = likeChkRepository.selectUserIdLikeChk(userId);

        List<LikeChkDTO> dtoList = new ArrayList<>();

        likeChkList.stream().map(list -> {
            LikeChkDTO likeChkDTO = LikeChkDTO.builder()
                    .likeNo(list.getLikeNo())
                    .userId(list.getUserId())
                    .pdNo(list.getPdNo())
                    .build();
            dtoList.add(likeChkDTO);
            return likeChkDTO;
        }).collect(Collectors.toList());

        return dtoList;
    }

    //좋아요
    @Override
    public void insertPdLike(String userId, Long pdNo) {
        LikeChk likeChk = LikeChk.builder()
                .userId(userId)
                .pdNo(pdNo)
                .build();

        likeChkRepository.save(likeChk);
        Optional<PdInfo> result = pdInfoRepository.selectPdInfoByPdNo(pdNo);
        PdInfo pdInfo = result.orElseThrow();

        pdInfo.changeLikeCnt(pdInfo.getLikeCnt() + 1);
        pdInfoRepository.save(pdInfo);
    }

    //좋아요 취소
    @Override
    public void removePdLike(String userId, Long pdNo) {
        likeChkRepository.removePdLike(userId,pdNo);
        Optional<PdInfo> result = pdInfoRepository.selectPdInfoByPdNo(pdNo);
        PdInfo pdInfo = result.orElseThrow();
        if(pdInfo.getLikeCnt() != 0){
            pdInfo.changeLikeCnt(pdInfo.getLikeCnt() - 1);
        }
        pdInfoRepository.save(pdInfo);
    }
}
