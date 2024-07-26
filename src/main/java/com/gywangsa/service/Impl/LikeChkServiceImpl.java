package com.gywangsa.service.Impl;

import com.gywangsa.domain.LikeChk;
import com.gywangsa.domain.PdFile;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.LikeChkDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.repository.LikeChkRepository;
import com.gywangsa.repository.MemberRepository;
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

    @Override
    public List<PdInfoDTO> selectUserIdLikePdInfo(String userId) {

        List<LikeChk> likeChkList = likeChkRepository.selectUserIdLikeChk(userId);
        List<PdInfoDTO> pdInfoDTOList = new ArrayList<>();

        for(int i = 0; i < likeChkList.size(); i++){

            Optional<PdInfo> result = pdInfoRepository.selectPdInfoByPdNo(likeChkList.get(i).getPdNo());
            PdInfo pdInfo = result.orElseThrow();

            PdInfoDTO pdInfoDTO = PdInfoDTO.builder()
                    .categoryNo(pdInfo.getCategoryNo())
                    .itemNo(pdInfo.getItemNo())
                    .brandNo(pdInfo.getBrandNo())
                    .brandNm(pdInfo.getBrandNm())
                    .pdNo(pdInfo.getPdNo())
                    .startDate(pdInfo.getStartDate())
                    .pdName(pdInfo.getPdName())
                    .endDate(pdInfo.getEndDate())
                    .buyAmt(pdInfo.getBuyAmt())
                    .likeCnt(pdInfo.getLikeCnt())
                    .sexCd(pdInfo.getSexCd())
                    .likeFlag(true)
                    .note(pdInfo.getNote())
                    .build();

            //이미지
            List<PdFile> imageList = pdInfo.getFileList();

            List<String> fileList = imageList.stream().map(m -> m.getFileNm()).toList();
            pdInfoDTO.setImageList(fileList);

            pdInfoDTOList.add(pdInfoDTO);

        }

        return pdInfoDTOList;
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
