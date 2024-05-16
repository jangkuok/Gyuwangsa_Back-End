package com.gywangsa.repository;


import com.gywangsa.domain.QPdCategory;
import com.gywangsa.domain.QPdItem;
import com.gywangsa.service.PdCategoryService;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class PdCategoryServiceTest {

    @Autowired
    PdCategoryService categoryService;

    @Transactional
    @Test
    public void selectListCategory(){
        categoryService.selectListCategory();

    }
}
