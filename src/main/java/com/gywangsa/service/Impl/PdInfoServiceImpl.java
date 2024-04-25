package com.gywangsa.service.Impl;

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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void modifyPdInfo(PdInfoDTO dto) {

        PdInfoPk pdInfoPk = new PdInfoPk();

        pdInfoPk.setCategoryNo(dto.getCategoryNo());
        pdInfoPk.setItemNo(dto.getItemNo());
        pdInfoPk.setPdNo(dto.getPdNo());
        pdInfoPk.setBrandNo(dto.getBrandNo());
        pdInfoPk.setStartDate(dto.getStartDate());

        Optional<PdInfo> result = pdInfoRepository.findById(pdInfoPk);

        PdInfo pdInfo = result.orElseThrow();

        pdInfo.setPdName(dto.getPdName());
        pdInfo.setBuyAmt(dto.getBuyAmt());
        pdInfo.setPdImage(dto.getPdImage());
        pdInfo.setNote(dto.getNote());

        pdInfoRepository.save(pdInfo);
    }

    //상품 삭제
    @Override
    public void removePdInfoByPdNo(Long categoryNo, Long itemNo, Long pdNo, Long brandNo, LocalDate startDate) {

        PdInfoPk pdInfoPk = new PdInfoPk();

        pdInfoPk.setCategoryNo(categoryNo);
        pdInfoPk.setItemNo(itemNo);
        pdInfoPk.setPdNo(pdNo);
        pdInfoPk.setBrandNo(brandNo);
        pdInfoPk.setStartDate(startDate);

        pdInfoRepository.deleteById(pdInfoPk);
    }

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

    //특정 상품 조회
    @Override
    public PdInfoDTO selectPdInfoByPdNo(Long categoryNo, Long itemNo, Long pdNo, Long brandNo, LocalDate startDate) {

        PdInfoPk pdInfoPk = new PdInfoPk();

        pdInfoPk.setCategoryNo(categoryNo);
        pdInfoPk.setItemNo(itemNo);
        pdInfoPk.setPdNo(pdNo);
        pdInfoPk.setBrandNo(brandNo);
        pdInfoPk.setStartDate(startDate);

        Optional<PdInfo> result = pdInfoRepository.findById(pdInfoPk);
        PdInfo pdInfo = result.orElseThrow();

        return entityPdInfo(pdInfo);
    }
    
    
}
