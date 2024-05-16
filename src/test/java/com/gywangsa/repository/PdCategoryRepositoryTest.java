package com.gywangsa.repository;

import com.gywangsa.domain.PdCategory;
import com.gywangsa.domain.PdItem;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class PdCategoryRepositoryTest {

    @Autowired
    private PdCategoryRepository categoryRepository;

    @Test
    public void insertCategory(){
        PdCategory category = PdCategory.builder()
                .categoryNo(4)
                .categoryNm("신발")
                .build();
        PdCategory result = categoryRepository.save(category);
        log.info(result);
    }

    @Test
    public void select(){
        List<PdCategory> list = categoryRepository.selectCategoryItem();
        for(PdCategory p:list){
            log.info(p);
            log.info(p.getPdItemList());
        }

    }

}
