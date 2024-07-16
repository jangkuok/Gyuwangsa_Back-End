package com.gywangsa.repository;


import com.gywangsa.domain.Color;
import com.gywangsa.dto.ColorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ColorRepository extends JpaRepository<Color,Long> {

    @Query("select c from Color c")
    List<Color> selectListColor();

}
