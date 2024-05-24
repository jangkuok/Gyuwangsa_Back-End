package com.gywangsa.controller;

import com.gywangsa.dto.PdCategoryDTO;
import com.gywangsa.dto.PdItemDTO;
import com.gywangsa.service.PdCategoryService;
import com.gywangsa.service.PdItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/category")
public class PdCategoryItemController {

    private final PdCategoryService categoryService;
    private final PdItemService pdItemService;

    @GetMapping("/")
    public List<PdCategoryDTO> selectListCategory(){
        return categoryService.selectListCategory();
    }
    @GetMapping("/item/{categoryNo}")
    public List<PdItemDTO> selectListItem(@PathVariable("categoryNo") Long categoryNo){
        return pdItemService.selectListItem(categoryNo);
    }
}
