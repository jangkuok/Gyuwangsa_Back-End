package com.gywangsa.repository;

import com.gywangsa.domain.PdItem;
import com.gywangsa.dto.PdItemDTO;
import com.gywangsa.service.PdItemService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class PdItemServiceTest {

    @Autowired
    private PdItemService pdItemService;

    @Test
    public void selectListCategory(){
       List<PdItemDTO> list = pdItemService.selectListItem(1L);
       log.info(list);
    }


}
