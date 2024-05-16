package com.gywangsa.controller;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.pk.PdInfoPk;
import com.gywangsa.service.PdInfoService;
import com.gywangsa.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
public class PdInfoController {

    private final PdInfoService pdInfoService;
    private final CustomFileUtil customFileUtil;

//    @GetMapping("/{brandNo}/{categoryNo}/{itemNo}/{pdNo}")
//    public PdInfoDTO selectPdInfoByPdNo(@PathVariable("categoryNo") Long categoryNo,
//                                     @PathVariable("itemNo") Long itemNo,
//                                     @PathVariable("brandNo") Long brandNo,
//                                     @PathVariable("pdNo") Long pdNo){
//        log.info("==============>selectPdInfoByPdNo");
//        return pdInfoService.selectPdInfoByPdNo(brandNo,categoryNo,itemNo,pdNo);
//    }

    @GetMapping("/{pdNo}")
    public PdInfoDTO selectPdInfoByPdNo(@PathVariable("pdNo") Long pdNo){
        log.info("==============>selectPdInfoByPdNo");
        return pdInfoService.selectPdInfoByPdNo(pdNo);
    }


    @GetMapping("/productList")
    public PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO){
        log.info("==============>selectListByPdInfo");
        return pdInfoService.selectListByPdInfo(pageRequestDTO);
    }

    @PostMapping("/insertPdInfo")
    public Map<String, Long> insertPdInfo(@RequestBody PdInfoDTO dto){
        log.info("==============>insertPdInfo" + dto);

        LocalDateTime now = LocalDateTime.now();
        dto.setStartDate(now);
        dto.setEndDate(now.plusMonths(12));

        Long pdNo = pdInfoService.insertPdInfo(dto);
        return Map.of("pdNo",pdNo);
    }

    @PostMapping("/test/insertPdInfo")
    public Map<String, String> testInsertPdInfo(PdInfoDTO dto){
        log.info("==============>insertPdInfo" + dto);

        List<MultipartFile> files = dto.getFiles();

        List<String> uploadFileName = customFileUtil.saveFile(files);

        dto.setImageList(uploadFileName);

        LocalDateTime now = LocalDateTime.now();
        dto.setStartDate(now);
        dto.setEndDate(now.plusMonths(12));

        log.info(uploadFileName);

        //Long pdNo = pdInfoService.insertPdInfo(dto);
        return Map.of("RESULT","SUCCESS");
    }

    @PutMapping("/modify/{brandNo}/{categoryNo}/{itemNo}/{pdNo}")
    public Map<String, String> modifyPdInfoByPdNo(@PathVariable("categoryNo") Long categoryNo,
                                            @PathVariable("itemNo") Long itemNo,
                                            @PathVariable("brandNo") Long brandNo,
                                            @PathVariable("pdNo") Long pdNo,
                                            @RequestBody PdInfoDTO dto){

        log.info("==============>modifyPdInfo" + dto);

        dto.setCategoryNo(categoryNo);
        dto.setItemNo(itemNo);
        dto.setBrandNo(brandNo);
        dto.setPdNo(pdNo);

        PdInfoPk pk = new PdInfoPk();

        pk.setCategoryNo(categoryNo);
        pk.setItemNo(itemNo);
        pk.setBrandNo(brandNo);
        pk.setPdNo(pdNo);

        pdInfoService.modifyPdInfoByPdNo(dto);
        return Map.of("RESULT","SUCCESS");
    }

    @DeleteMapping("/remove/{brandNo}/{categoryNo}/{itemNo}/{pdNo}")
    public Map<String, String> removePdInfoByPdNo(@PathVariable("categoryNo") Long categoryNo,
                                            @PathVariable("itemNo") Long itemNo,
                                            @PathVariable("brandNo") Long brandNo,
                                            @PathVariable("pdNo") Long pdNo,
                                            @RequestBody PdInfoDTO dto){

        log.info("==============>removePdInfoByPdNo" + dto);

        pdInfoService.removePdInfoByPdNo(brandNo,categoryNo,itemNo,pdNo);
        return Map.of("RESULT","SUCCESS");
    }

}
