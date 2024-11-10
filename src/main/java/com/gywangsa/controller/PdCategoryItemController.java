package com.gywangsa.controller;

import com.gywangsa.domain.PdCategory;
import com.gywangsa.dto.PdCategoryDTO;
import com.gywangsa.dto.PdItemDTO;
import com.gywangsa.service.PdCategoryService;
import com.gywangsa.service.PdItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/brand/category")
    public List<PdCategoryDTO> selectCategory(){
        return categoryService.selectCategory();
    }

    @GetMapping("/brand/item/{categoryNo}")
    public List<PdItemDTO> selectListBrandItem(@PathVariable("categoryNo") Long categoryNo){
        return pdItemService.selectListItem(categoryNo);
    }


    @PostMapping("/add")
    public String insetCategoryItem(@RequestPart(value = "category") PdCategoryDTO categoryDTO,
                                    @RequestPart(value = "item") List<PdItemDTO> itemDTO){


        //categoryService.insertCategory(categoryDTO);


        for (int i = 0; i < itemDTO.size(); i++){
            pdItemService.insertItem(itemDTO.get(i),1);
        }



        return "성공";
    }
}
