package com.gywangsa.repository;

import com.gywangsa.domain.Color;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class ColorRepositoryTest {

    @Autowired
    private ColorRepository colorRepository;


    @Test
    public void insertColor(){
        Color color = Color.builder()
                .colorNm("")
                .build();

        colorRepository.save(color);
    }

    @Test
    public void selectList(){
        log.info(colorRepository.selectListColor());
    }
}
