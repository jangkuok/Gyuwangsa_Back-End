package com.gywangsa.controller;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.domain.PdSize;
import com.gywangsa.dto.*;
import com.gywangsa.pk.PdInfoPk;
import com.gywangsa.service.LikeChkService;
import com.gywangsa.service.PdInfoService;
import com.gywangsa.util.CustomFileUtil;
import com.gywangsa.util.JWTUtil;
import com.sun.security.auth.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
public class PdInfoController {

    private final PdInfoService pdInfoService;
    private final CustomFileUtil customFileUtil;
    private final LikeChkService likeChkService;


    //상품 조회
    @GetMapping("/info/{pdNo}")
    public PdInfoDTO selectPdInfoByPdNo(@PathVariable("pdNo") Long pdNo) {
        log.info("==============>selectPdInfoByPdNo");

        PdInfoDTO pdInfoDTO = pdInfoService.selectPdInfoByPdNo(pdNo);

        log.info(pdInfoDTO);

        return pdInfoDTO;
    }

    //상품 이미지
    @GetMapping("/view/{fileNm}")
    public ResponseEntity<Resource> viewFile(@PathVariable("fileNm") String fileNm) {
        log.info("==============>viewFile");
        return customFileUtil.selectByFile(fileNm);
    }


    //목록 조회
    //@PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/item/{categoryNo}/{itemNo}")
    public PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO,
                                                         @PathVariable("categoryNo") Long categoryNo,
                                                         @PathVariable("itemNo") Long itemNo
                                                        ,@RequestParam("userId") String userId) {

        log.info("==============>selectListByPdInfo");

        PageResponseDTO<PdInfoDTO> pdInfoDTOPageResponseDTO = pdInfoService.selectListByPdInfo(pageRequestDTO, categoryNo, itemNo);

        List<LikeChkDTO> dtoList = likeChkService.selectUserIdLikeChk(userId);

        if(userId != null || !userId.equals("")){
            for(int i = 0; i < dtoList.size();i++){
                for(int j = 0; j < pdInfoDTOPageResponseDTO.getDtoList().size(); j++){
                    if(dtoList.get(i).getPdNo().equals(pdInfoDTOPageResponseDTO.getDtoList().get(j).getPdNo())){
                        pdInfoDTOPageResponseDTO.getDtoList().get(j).setLikeFlag(true);
                        log.info(pdInfoDTOPageResponseDTO.getDtoList().get(j));
                    }
                }
            }
        }

        log.info(pdInfoDTOPageResponseDTO);
        return pdInfoDTOPageResponseDTO;
    }


    //등록
    @PostMapping("/insertPdInfo")
    public Map<String, Long> insertPdInfo(@RequestPart(value = "pdInfo") PdInfoDTO pdInfoDTO,
                                          @RequestPart(value = "fileList", required = false) List<MultipartFile> fileList,
                                          @RequestPart(value = "colorList", required = false) String[] colorList) {


        //이미지 등록
        List<String> fileNames = customFileUtil.saveFile(fileList);
        pdInfoDTO.setImageList(fileNames);

        //날짜 등록
        LocalDateTime now = LocalDateTime.now();
        pdInfoDTO.setStartDate(now);
        pdInfoDTO.setEndDate(now.plusMonths(12));

        //사이즈 등록
        List<PdSizeDTO> sizeDTOList = new ArrayList<>();


        for (int j = 0; j < pdInfoDTO.getSizeList().size(); j++) {
            for (int i = 0; i < colorList.length; i++) {

                PdSizeDTO dto = new PdSizeDTO();

                dto.setAttr1(pdInfoDTO.getSizeList().get(j).getAttr1());
                dto.setAttr2(pdInfoDTO.getSizeList().get(j).getAttr2());
                dto.setAttr3(pdInfoDTO.getSizeList().get(j).getAttr3());
                dto.setAttr4(pdInfoDTO.getSizeList().get(j).getAttr4());
                dto.setAttr5(pdInfoDTO.getSizeList().get(j).getAttr5());
                dto.setAttr6(pdInfoDTO.getSizeList().get(j).getAttr6());
                dto.setAttr7(pdInfoDTO.getSizeList().get(j).getAttr7());
                dto.setColorCode(pdInfoDTO.getSizeList().get(j).getColorCode());
                dto.setColor(colorList[i]);
                dto.setPdType(pdInfoDTO.getSizeList().get(j).getPdType());
                dto.setSizeCnt(pdInfoDTO.getSizeList().get(j).getSizeCnt());
                dto.setSizeType(pdInfoDTO.getSizeList().get(j).getSizeType());

                log.info(dto);

                sizeDTOList.add(dto);
            }
        }


        for (int i = 0; i < sizeDTOList.size(); i++) {
            log.info(sizeDTOList.get(i));
        }

        pdInfoDTO.getSizeList().clear();

        for (int i = 0; i < sizeDTOList.size(); i++) {
            pdInfoDTO.getSizeList().add(sizeDTOList.get(i));
        }

        log.info("==============>insertPdInfo" + pdInfoDTO);

        Long pdNo = pdInfoService.insertPdInfo(pdInfoDTO);

        return Map.of("pdNo", pdNo);

    }

//    //등록
//    @PostMapping("/test/insertPdInfo")
//    public Map<String, String> testInsertPdInfo(PdInfoDTO dto){
//        log.info("==============>insertPdInfo" + dto);
//
//        List<MultipartFile> files = dto.getFiles();
//
//        List<String> uploadFileName = customFileUtil.saveFile(files);
//
//        dto.setImageList(uploadFileName);
//
//        LocalDateTime now = LocalDateTime.now();
//        dto.setStartDate(now);
//        dto.setEndDate(now.plusMonths(12));
//
//        log.info(uploadFileName);
//
//        //Long pdNo = pdInfoService.insertPdInfo(dto);
//        return Map.of("RESULT","SUCCESS");
//    }

//    //수정
//    @PutMapping("/modify/{brandNo}/{categoryNo}/{itemNo}/{pdNo}")
//    public Map<String, String> modifyPdInfoByPdNo(@PathVariable("categoryNo") Long categoryNo,
//                                            @PathVariable("itemNo") Long itemNo,
//                                            @PathVariable("brandNo") Long brandNo,
//                                            @PathVariable("pdNo") Long pdNo,
//                                            @RequestBody PdInfoDTO dto){
//
//        log.info("==============>modifyPdInfo" + dto);
//
//        dto.setCategoryNo(categoryNo);
//        dto.setItemNo(itemNo);
//        dto.setBrandNo(brandNo);
//        dto.setPdNo(pdNo);
//
//        PdInfoPk pk = new PdInfoPk();
//
//        pk.setCategoryNo(categoryNo);
//        pk.setItemNo(itemNo);
//        pk.setBrandNo(brandNo);
//        pk.setPdNo(pdNo);
//
//        pdInfoService.modifyPdInfoByPdNo(dto);
//        return Map.of("RESULT","SUCCESS");
//    }


    //수정
    @PutMapping("/modify/{pdNo}")
    public Map<String, String> modifyPdInfoByPdNo(
            @PathVariable("pdNo") Long pdNo,
            @RequestPart(value = "pdInfo") PdInfoDTO dto,
            @RequestPart(value = "fileList", required = false) List<MultipartFile> files) {

        dto.setPdNo(dto.getPdNo());

        //수정 전 파일
        PdInfoDTO oldDto = pdInfoService.selectPdInfoByPdNo(dto.getPdNo());

        //파일 업로드
        //List<MultipartFile> files = dto.getFiles();
        List<String> uploadFileNames = customFileUtil.saveFile(files);

        //수정 안 한 파일
        List<String> noModifyFileNames = dto.getImageList();

        //업로드 된 파일이 있으면
        if (uploadFileNames != null && !uploadFileNames.isEmpty()) {
            noModifyFileNames.addAll(uploadFileNames);
        }

        pdInfoService.modifyPdInfoByPdNo(dto);

        List<String> oldFileNames = oldDto.getImageList();
        if (oldFileNames != null && oldFileNames.size() > 0) {
            List<String> removeFileNames = oldFileNames.stream().filter(m -> noModifyFileNames.indexOf(m) == -1).collect(Collectors.toList());
            customFileUtil.deleteFiles(removeFileNames);
        }

        return Map.of("RESULT", "SUCCESS");
    }

    //삭제
    @DeleteMapping("/remove/{pdNo}")
    public Map<String, String> removePdInfoByPdNo(@PathVariable("pdNo") Long pdNo) {

        List<String> fileNames = pdInfoService.selectPdInfoByPdNo(pdNo).getImageList();
        pdInfoService.removePdInfoByPdNo(pdNo);

        customFileUtil.deleteFiles(fileNames);

        return Map.of("RESULT", "SUCCESS");
    }

    //상품 이름 조회
    @GetMapping("/search/{keyword}")
    public PageResponseDTO<PdInfoDTO> selectPdInfoByKeyword(PageRequestDTO pageRequestDTO, @PathVariable("keyword") String keyword, @RequestParam("userId") String userId){
        log.info("-------------------PdInfoController-------------------");
        log.info("============상품 키워드 조회============");

        PageResponseDTO<PdInfoDTO> pdInfoDTOPageResponseDTO = pdInfoService.selectBrandByKeyword(pageRequestDTO,keyword);

        List<LikeChkDTO> dtoList = likeChkService.selectUserIdLikeChk(userId);

        if(userId != null || !userId.equals("")){
            for(int i = 0; i < dtoList.size();i++){
                for(int j = 0; j < pdInfoDTOPageResponseDTO.getDtoList().size(); j++){
                    if(dtoList.get(i).getPdNo().equals(pdInfoDTOPageResponseDTO.getDtoList().get(j).getPdNo())){
                        pdInfoDTOPageResponseDTO.getDtoList().get(j).setLikeFlag(true);
                        log.info(pdInfoDTOPageResponseDTO.getDtoList().get(j));
                    }
                }
            }
        }

        return pdInfoDTOPageResponseDTO;
    }

}
