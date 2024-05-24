package com.gywangsa.service.Impl;

import com.gywangsa.domain.PdFile;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.pk.PdInfoPk;
import com.gywangsa.repository.PdInfoRepository;
import com.gywangsa.service.PdInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PdInfoServiceImpl implements PdInfoService {

    private final PdInfoRepository pdInfoRepository;

    //상품 등록
    @Override
    public Long insertPdInfo(PdInfoDTO dto) {

        PdInfo pdInfo = dtoPdInfo(dto);

        PdInfo result = pdInfoRepository.save(pdInfo);

        return result.getPdNo();
    }

    //상품 수정
    @Override
    public void modifyPdInfoByPdNo(PdInfoDTO pdInfoDTO) {
        //조회
        Optional<PdInfo> result = pdInfoRepository.selectPdInfoByPdNo(pdInfoDTO.getPdNo());
        PdInfo pdInfo = result.orElseThrow();
        //수정
        pdInfo.changePdName(pdInfoDTO.getPdName());
        pdInfo.changeBuyAmt(pdInfoDTO.getBuyAmt());
        pdInfo.changeNote(pdInfoDTO.getNote());

        //이미지 처리
        List<String> fileNames = pdInfoDTO.getImageList();

        pdInfo.delFileList();

        if(fileNames != null && !fileNames.isEmpty()){
            fileNames.forEach(fe -> {
                pdInfo.addFileString(fe);
            });
        }
        //저장
        pdInfoRepository.save(pdInfo);
    }

    //상품 삭제
    @Override
    public void removePdInfoByPdNo(Long pdNo) {

        pdInfoRepository.removePdInfoByPdNo(pdNo);
    }
/*

    //상품 리스트 조회
    @Override
    public PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO) {
        //JPA
        Page<PdInfo> result = pdInfoRepository.selectListByPdInfo(pageRequestDTO);

        //Entity List => DTO List 변형
        List<PdInfoDTO> pdInfoDTOList = result.
                get().
                map(pdInfo -> entityPdInfo(pdInfo)).collect(Collectors.toList());

        //리스트 담기
        PageResponseDTO<PdInfoDTO> responseDTO =
                PageResponseDTO.<PdInfoDTO>pageResponseDTOMethod()
                .dtoList(pdInfoDTOList)
                .pageRequestDTO(pageRequestDTO)
                .total(result.getTotalElements())
                .build();

        return responseDTO;
    }
*/

    @Override
    public PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO,Long categoryNo, Long itemNo) {

        //페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("pdNo").descending());
        //JPA
        Page<Object[]> result = pdInfoRepository.selectListItemPdInfo(pageable,categoryNo,itemNo);


        List<PdInfoDTO> dtoList = result.get().map(m -> {
            PdInfoDTO pdInfoDTO = null;
            PdInfo pdInfo = (PdInfo) m[0];
            PdFile pdFile = (PdFile) m[1];

            pdInfoDTO = entityPdInfo(pdInfo);

            String imgStr = pdFile.getFileNm();
            pdInfoDTO.setImageList(List.of(imgStr));

            return pdInfoDTO;
        }).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<PdInfoDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //특정 상품 조회
/*    @Override
    public PdInfoDTO selectPdInfoByPdNo(Long brandNo, Long categoryNo, Long itemNo, Long pdNo) {

        PdInfoPk pdInfoPk = new PdInfoPk();

        pdInfoPk.setCategoryNo(categoryNo);
        pdInfoPk.setItemNo(itemNo);
        pdInfoPk.setPdNo(pdNo);
        pdInfoPk.setBrandNo(brandNo);

        Optional<PdInfo> result = pdInfoRepository.findById(pdInfoPk);
        PdInfo pdInfo = result.orElseThrow();

        return entityPdInfo(pdInfo);
    }*/
    @Override
    public PdInfoDTO selectPdInfoByPdNo(Long pdNo) {

        Optional<PdInfo> result =pdInfoRepository.selectPdInfoByPdNo(pdNo);

        PdInfo pdInfo = result.orElseThrow();

        return entityPdInfo(pdInfo);
    }
    
    
}
