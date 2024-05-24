package com.gywangsa.repository;

import com.gywangsa.domain.PdItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PdItemRepository extends JpaRepository<PdItem,Long> {
    @Query("select i from PdItem i join fetch i.pdCategory c where c.categoryNo = :categoryNo")
    List<PdItem> selectListItem(@Param("categoryNo") Long categoryNo);
}
