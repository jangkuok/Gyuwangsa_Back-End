package com.gywangsa.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class OrderDtlDTO {

    private String userId;

    private Long ordDtlNo;

    private LocalDateTime ordDate;

    private String deliNo;

    private String deliStatus;

    private String deliAmt;

    private String phone;

    private String addrNo;

    private String addr;

    private String addrDtl;

    private String size;

    private String color;

    private int buyAmt;

    private int count;

    private Long brandNo;

    private String brandNm;

    private Long pdNo;

    private String pdName;

    private String imageFile;

    public OrderDtlDTO(String userId,Long ordDtlNo, LocalDateTime ordDate, String deliNo, String deliStatus, String deliAmt, String phone, String addrNo, String addr, String addrDtl, String size, String color, int buyAmt, int count, Long brandNo, String brandNm, Long pdNo, String pdName, String imageFile) {
        this.userId = userId;
        this.ordDtlNo = ordDtlNo;
        this.ordDate = ordDate;
        this.deliNo = deliNo;
        this.deliStatus = deliStatus;
        this.deliAmt = deliAmt;
        this.phone = phone;
        this.addrNo = addrNo;
        this.addr = addr;
        this.addrDtl = addrDtl;
        this.size = size;
        this.color = color;
        this.buyAmt = buyAmt;
        this.count = count;
        this.brandNo = brandNo;
        this.brandNm = brandNm;
        this.pdNo = pdNo;
        this.pdName = pdName;
        this.imageFile = imageFile;
    }
}
