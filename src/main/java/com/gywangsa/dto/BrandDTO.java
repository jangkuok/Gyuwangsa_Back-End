package com.gywangsa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {
    private Long brandNo;

    private String brandNm;

    private String engNm;

    private String brandLog;

    private String brandMainImage;

    private String addrNo;

    private String addr;

    private String addrDtl;

    private String comCall;

    private String comEmail;

    private String deliComp;

    private boolean stateCd ;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String note;
}
