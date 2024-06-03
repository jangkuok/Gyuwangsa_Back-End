package com.gywangsa.controller;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.domain.PdSize;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.dto.PdSizeDTO;
import com.gywangsa.pk.PdInfoPk;
import com.gywangsa.service.PdInfoService;
import com.gywangsa.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


     //상품 조회
     @GetMapping("/{pdNo}")
     public PdInfoDTO selectPdInfoByPdNo(@PathVariable("pdNo") Long pdNo){
        log.info("==============>selectPdInfoByPdNo");
        return pdInfoService.selectPdInfoByPdNo(pdNo);
     }

    @GetMapping("/view/{fileNm}")
    public ResponseEntity<Resource> viewFile(@PathVariable("fileNm") String fileNm){
        log.info("==============>viewFile");
        return customFileUtil.selectByFile(fileNm);
    }


     //목록 조회
    @GetMapping("/item/{categoryNo}/{itemNo}")
    public PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO,
                                                         @PathVariable("categoryNo") Long categoryNo,
                                                         @PathVariable("itemNo") Long itemNo){
        log.info("==============>selectListByPdInfo");
        return pdInfoService.selectListByPdInfo(pageRequestDTO,categoryNo,itemNo);
    }


    //등록
    @PostMapping("/insertPdInfo")
    //public Map<String, Long> insertPdInfo(PdInfoDTO dto){
//    public Map<String, Long> insertPdInfo(PdInfoDTO dto, @RequestPart(value = "size") List<String> sizeList,
//                                          @RequestPart(value = "file",required = false) List<MultipartFile> files){
        public Map<String, Long> insertPdInfo(@RequestPart(value = "pdInfo") PdInfoDTO pdInfoDTO,
                                                             @RequestPart(value = "fileList",required = false)  List<MultipartFile> fileList){

         log.info(pdInfoDTO.getPdName());
         log.info(pdInfoDTO.getSizeList());
         log.info(fileList);

        //List<MultipartFile> files = dto.getFiles();
        //List<String> fileNames = customFileUtil.saveFile(files);
        //dto.setImageList(fileNames);

        List<String> fileNames = customFileUtil.saveFile(fileList);
        pdInfoDTO.setImageList(fileNames);

        pdInfoDTO.setSizeList(pdInfoDTO.getSizeList());

        LocalDateTime now = LocalDateTime.now();
        pdInfoDTO.setStartDate(now);
        pdInfoDTO.setEndDate(now.plusMonths(12));

        log.info("==============>insertPdInfo" + pdInfoDTO);

        Long pdNo = pdInfoService.insertPdInfo(pdInfoDTO);

        return Map.of("pdNo",pdNo);

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
    public Map<String, String> modifyPdInfoByPdNo(@PathVariable("pdNo") Long pdNo,
                                                  PdInfoDTO dto){
        dto.setPdNo(pdNo);

        //수정 전 파일
        PdInfoDTO oldDto = pdInfoService.selectPdInfoByPdNo(pdNo);

        //파일 없로드
        List<MultipartFile> files = dto.getFiles();
        List<String> uploadFileNames = customFileUtil.saveFile(files);

        //수정 안 한 파일
        List<String> noModifyFileNames = dto.getImageList();

        //업로드 된 파일이 있으면
        if(uploadFileNames != null && !uploadFileNames.isEmpty()){
            noModifyFileNames.addAll(uploadFileNames);
        }

        pdInfoService.modifyPdInfoByPdNo(dto);

        List<String> oldFileNames = oldDto.getImageList();
        if(oldFileNames != null && oldFileNames.size() > 0){
            List<String> removeFileNames = oldFileNames.stream().filter(m -> noModifyFileNames.indexOf(m) == -1).collect(Collectors.toList());
            customFileUtil.deleteFiles(removeFileNames);
        }

        return Map.of("RESULT","SUCCESS");
    }

    //삭제
    @DeleteMapping("/remove/{pdNo}")
    public Map<String, String> removePdInfoByPdNo(@PathVariable("pdNo")Long pdNo){

        List<String> fileNames = pdInfoService.selectPdInfoByPdNo(pdNo).getImageList();
        pdInfoService.removePdInfoByPdNo(pdNo);

        customFileUtil.deleteFiles(fileNames);

        return Map.of("RESULT","SUCCESS");
    }

}
