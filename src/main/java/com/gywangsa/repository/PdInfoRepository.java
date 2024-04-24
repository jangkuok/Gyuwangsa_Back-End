package com.gywangsa.repository;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.paging.PdInfoPaging;
import com.gywangsa.pk.PdInfoPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdInfoRepository extends JpaRepository<PdInfo, PdInfoPk>, PdInfoPaging {
}
