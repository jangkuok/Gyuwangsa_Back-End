package com.gywangsa.controller;

import com.gywangsa.dto.ColorDTO;
import com.gywangsa.service.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/color")
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/colorList")
    public List<ColorDTO> selectListColor(){

        log.info("-------------------ColorController-------------------");
        log.info("============색상 목록============");

        List<ColorDTO> colorList = colorService.selectListColor();

        return colorList;
    }
}
