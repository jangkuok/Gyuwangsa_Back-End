package com.gywangsa.repository;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.domain.PdItem;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class PdItemRepositoryTest {

    @Autowired
    private PdItemRepository pdItemRepository;

    @Test
    public void selectListItem() {
        Long categoryNo = 1L;

        List<PdItem> pdItemList = pdItemRepository.selectListItem(categoryNo);
        log.info(pdItemList);
    }
}
