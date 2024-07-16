package com.gywangsa.service.Impl;

import com.gywangsa.domain.PdFile;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.domain.PdSize;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PdInfoServiceImpl implements PdInfoService {

    private final PdInfoRepository pdInfoRepository;


    private Pageable getPageable(PageRequestDTO pageRequestDTO){
            Pageable pageable = PageRequest.of(
            pageRequestDTO.getPage() -1,
            pageRequestDTO.getSize(),
            Sort.by("pdNo").descending());
            return  pageable;
    }

    private List<PdInfoDTO> getPdInfoDTOList(Page<Object[]> page){

        List<PdInfoDTO> dtoList = page.get().map(m -> {

            PdInfo pdInfo = (PdInfo) m[0];
            PdFile pdFile = (PdFile) m[1];

            PdInfoDTO pdInfoDTO = entityPdInfo(pdInfo);

            String imgStr = pdFile.getFileNm();
            pdInfoDTO.setImageList(List.of(imgStr));

            return pdInfoDTO;

        }).collect(Collectors.toList());

        return dtoList;
    }

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

        //사이즈 처리
        pdInfo.delSizeList();

        List<PdSize> pdSizeList = pdInfoDTO.getSizeList().stream().map(m -> PdSize.builder()
                .sizeType(m.getSizeType())
                .pdType(m.getPdType())
                .attr1(m.getAttr1())
                .attr2(m.getAttr2())
                .attr3(m.getAttr3())
                .attr4(m.getAttr4())
                .attr5(m.getAttr5())
                .attr6(m.getAttr6())
                .attr7(m.getAttr7())
                .color(m.getColor())
                .colorCode(m.getColorCode())
                .sizeCnt(m.getSizeCnt())
                .build()).collect(Collectors.toList());

        pdInfo.changeSizeList(pdSizeList);

        //저장
        pdInfoRepository.save(pdInfo);
    }

    //상품 삭제
    @Override
    public void removePdInfoByPdNo(Long pdNo) {

        pdInfoRepository.removePdInfoByPdNo(pdNo);
    }

    //상품 목록
    @Override
    public PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO,Long categoryNo, Long itemNo) {

        //페이징 처리
        Pageable pageable = getPageable(pageRequestDTO);

        //JPA
        Page<Object[]> result = pdInfoRepository.selectListItemPdInfo(pageable,categoryNo,itemNo);


        List<PdInfoDTO> dtoList = getPdInfoDTOList(result);

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<PdInfoDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //특정 브랜드 조회
    @Override
    public PageResponseDTO<PdInfoDTO> selectListByBrandPdInfo(PageRequestDTO pageRequestDTO, Long brandNo) {
        //페이징 처리
        Pageable pageable = getPageable(pageRequestDTO);

        //JPA
        Page<Object[]> result = pdInfoRepository.selectListByBrandPdInfo(pageable,brandNo);

        List<PdInfoDTO> dtoList = getPdInfoDTOList(result);

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<PdInfoDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //특정 브랜드 카테고리 조회
    @Override
    public PageResponseDTO<PdInfoDTO> selectListByBrandCategory(PageRequestDTO pageRequestDTO, Long categoryNo, Long brandNo) {
        //페이징 처리
        Pageable pageable = getPageable(pageRequestDTO);

        //JPA
        Page<Object[]> result = pdInfoRepository.selectListByBrandCategory(pageable,categoryNo,brandNo);

        List<PdInfoDTO> dtoList = getPdInfoDTOList(result);

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<PdInfoDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //브랜드 리스트 카테고리 아이템 조회
    @Override
    public PageResponseDTO<PdInfoDTO> selectListByBrandCategoryItem(PageRequestDTO pageRequestDTO, Long categoryNo, Long itemNo, Long brandNo) {

        Pageable pageable = getPageable(pageRequestDTO);

        Page<Object[]> result = pdInfoRepository.selectListByBrandCategoryItem(pageable,categoryNo,itemNo,brandNo);

        List<PdInfoDTO> dtoList = getPdInfoDTOList(result);

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<PdInfoDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //상품 키워드
    @Override
    public PageResponseDTO<PdInfoDTO> selectBrandByKeyword(PageRequestDTO pageRequestDTO,String keyword) {

        Pageable pageable = getPageable(pageRequestDTO);

        Page<Object[]> result = pdInfoRepository.findByPdNameContaining(pageable,keyword);

        List<PdInfoDTO> dtoList = getPdInfoDTOList(result);

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


    //상품 조회
    @Override
    public PdInfoDTO selectPdInfoByPdNo(Long pdNo) {

        Optional<PdInfo> result =pdInfoRepository.selectPdInfoByPdNo(pdNo);

        PdInfo pdInfo = result.orElseThrow();

        log.info(pdInfo);
        return entityPdInfo(pdInfo);
    }


}
