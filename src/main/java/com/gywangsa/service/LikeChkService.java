package com.gywangsa.service;


import com.gywangsa.domain.LikeChk;
import com.gywangsa.dto.LikeChkDTO;

import java.util.List;

public interface LikeChkService {
    List<LikeChkDTO> selectUserIdLikeChk(String userId);

    void insertPdLike(String userId, Long pdNo);

    void removePdLike(String userId, Long pdNo);
}
