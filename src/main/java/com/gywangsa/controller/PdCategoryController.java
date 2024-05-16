package com.gywangsa.controller;

import com.gywangsa.dto.PdCategoryDTO;
import com.gywangsa.service.PdCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/category")
public class PdCategoryController {

    private final PdCategoryService categoryService;

    @GetMapping("/")
    public List<PdCategoryDTO> selectListCategory(){
        return categoryService.selectListCategory();
    }
}
