package com.gywangsa.service.Impl;

import com.gywangsa.domain.Color;
import com.gywangsa.dto.ColorDTO;
import com.gywangsa.repository.ColorRepository;
import com.gywangsa.service.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public List<ColorDTO> selectListColor() {

        List<Color> colors = colorRepository.selectListColor();

        List<ColorDTO> dtoList = colors.stream().map(list ->
                       entityToDTO(list)).collect(Collectors.toList());

        return dtoList;
    }
}
