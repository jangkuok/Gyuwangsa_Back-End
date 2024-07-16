package com.gywangsa.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeChkDTO {

    private Long likeNo;
    private String userId;
    private Long pdNo;
}
