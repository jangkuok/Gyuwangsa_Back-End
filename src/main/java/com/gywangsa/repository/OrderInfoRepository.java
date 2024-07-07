package com.gywangsa.repository;

import com.gywangsa.domain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

    @Query("select orderInfo from OrderInfo orderInfo where orderInfo.member.userId = :userId")
    Optional<OrderInfo> selectOrderInfoMember(@Param("userId") String userId);
}
