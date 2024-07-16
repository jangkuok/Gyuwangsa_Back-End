package com.gywangsa.repository;

import com.gywangsa.dto.ColorDTO;
import com.gywangsa.service.ColorService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ColorServiceTest {
    @Autowired
    private ColorService colorService;

    @Test
    public void selectList(){

        List<ColorDTO> list = colorService.selectListColor();

        log.info(list);
    }

}
