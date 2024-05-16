package com.gywangsa.repository;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.paging.PdInfoPaging;
import com.gywangsa.pk.PdInfoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PdInfoRepository extends JpaRepository<PdInfo, PdInfoPk>, PdInfoPaging {

    @Query("select p from PdInfo p where p.pdNo= :pdNo")
    PdInfo selectPdInfoByPdNo(@Param("pdNo") Long pdNo);

}
