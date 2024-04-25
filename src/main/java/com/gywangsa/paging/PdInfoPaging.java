package com.gywangsa.paging;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface PdInfoPaging {
    Page<PdInfo> selectListByPdInfo(PageRequestDTO pageRequestDTO);
}
