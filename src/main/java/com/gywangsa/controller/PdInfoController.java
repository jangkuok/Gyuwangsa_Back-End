package com.gywangsa.controller;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.pk.PdInfoPk;
import com.gywangsa.service.PdInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
public class PdInfoController {

    private final PdInfoService pdInfoService;

    @GetMapping("/{brandNo}/{categoryNo}/{itemNo}/{pdNo}")
    public PdInfoDTO selectPdInfoByPdNo(@PathVariable("categoryNo") Long categoryNo,
                                     @PathVariable("itemNo") Long itemNo,
                                     @PathVariable("brandNo") Long brandNo,
                                     @PathVariable("pdNo") Long pdNo){
        log.info("==============>selectPdInfoByPdNo");
        return pdInfoService.selectPdInfoByPdNo(brandNo,categoryNo,itemNo,pdNo);
    }

    @GetMapping("/productList")
    public PageResponseDTO<PdInfoDTO> selectListByPdInfo(PageRequestDTO pageRequestDTO){
        log.info("==============>selectListByPdInfo");
        return pdInfoService.selectListByPdInfo(pageRequestDTO);
    }

    @PostMapping("/insertPdInfo")
    public Map<String, Long> insertPdInfo(@RequestBody PdInfoDTO dto){
        log.info("==============>insertPdInfo" + dto);
        Long pdNo = pdInfoService.insertPdInfo(dto);
        return Map.of("pdNo",pdNo);
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
