package com.gywangsa.repository;

import com.gywangsa.domain.PdCategory;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PdCategoryRepository extends JpaRepository<PdCategory,Long> {

    @Query("select c from PdCategory c join fetch c.pdItemList ")
    List<PdCategory> selectCategoryItem();

}
