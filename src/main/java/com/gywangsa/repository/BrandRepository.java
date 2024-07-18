package com.gywangsa.repository;

import com.gywangsa.domain.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("select b from Brand b where b.stateCd = true")
    Page<Brand> selectBrandList(Pageable pageable);

    @Query("select b from Brand b where b.brandNo = :brandNo")
    Brand selectBrandByBrandNo(@Param("brandNo") Long brandNo);

    @Query("select b from Brand b where b.brandNm like %:keyword% or b.engNm like %:keyword%")
    Page<Brand> findByBrandNmContaining(Pageable pageable,String keyword);

    @Query(value = "select * from gyu_sys_brand where state_cd = true  order by random() limit 20",nativeQuery = true)
    List<Brand> selectRandomBrandList();

}
