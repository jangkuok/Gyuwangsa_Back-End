package com.gywangsa.repository;

import com.gywangsa.domain.LikeChk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeChkRepository extends JpaRepository<LikeChk,Long> {


    @Query("select l from LikeChk l where l.userId = :userId order by l.likeNo desc ")
    List<LikeChk> selectUserIdLikeChk(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query("delete from LikeChk l where l.userId = :userId and l.pdNo = :pdNo")
    void removePdLike(@Param("userId") String userId, @Param("pdNo") Long pdNo);

}
