package com.gywangsa.paging;

import com.gywangsa.domain.PdInfo;
import org.springframework.data.domain.Page;

public interface PdInfoPaging {
    Page<PdInfo> search();
}
