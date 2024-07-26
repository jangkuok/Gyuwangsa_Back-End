package com.gywangsa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class LikeChkPdDTO {
    private Long likeChkPdNo;
    private String userId;
    private Long brandNo;
    private String brandNm;
    private String pdName;
    private int buyAmt;
    private String imageFile;

    public LikeChkPdDTO(Long likeChkPdNo, String userId, Long brandNo, String brandNm, String pdName, int buyAmt, String imageFile) {
        this.likeChkPdNo = likeChkPdNo;
        this.userId = userId;
        this.brandNo = brandNo;
        this.brandNm = brandNm;
        this.pdName = pdName;
        this.buyAmt = buyAmt;
        this.imageFile = imageFile;
    }
}
