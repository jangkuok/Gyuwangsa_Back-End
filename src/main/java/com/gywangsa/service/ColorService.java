package com.gywangsa.service;

import com.gywangsa.domain.Color;
import com.gywangsa.dto.ColorDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ColorService {

    List<ColorDTO> selectListColor();

    default ColorDTO entityToDTO(Color color){

        ColorDTO colorDTO = ColorDTO.builder()
                .colorNo(color.getColorNo())
                .colorNm(color.getColorNm())
                .build();

        return colorDTO;
    }

}
