package com.gywangsa.repository;

import com.gywangsa.domain.OrderDtl;
import com.gywangsa.domain.OrderInfo;
import com.gywangsa.dto.OrderDtlDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDtlRepository extends JpaRepository<OrderDtl,Long> {

    //주문 상태 수정
    @Modifying
    @Query("update OrderDtl odd set odd.deliStatus = :deliStatus where odd.ordDtlNo = :ordDtlNo")
    void modifyOrderDtlNo(@Param("ordDtlNo") Long ordDtlNo,
                          @Param("deliStatus") String deliStatus);

    //주문 배송지 수정
    @Modifying
    @Query("update OrderDtl odd set odd.addrNo = :addrNo, odd.addr = :addr, odd.addrDtl = :addrDtl where odd.ordDtlNo = :ordDtlNo")
    void modifyOrderAddr(@Param("ordDtlNo") Long ordDtlNo,
                          @Param("addrNo") String addrNo,
                         @Param("addr") String addr,
                         @Param("addrDtl") String addrDtl);

    //배송 상태 변경
    @Modifying
    @Query("update OrderDtl odd set odd.deliStatus = :deliStatus where odd.ordDtlNo = :ordDtlNo")
    void removeOrder(@Param("ordDtlNo") Long ordDtlNo, @Param("deliStatus") String deliStatus);

    //회원 주문 조회
    @Query("select new com.gywangsa.dto.OrderDtlDTO(oi.member.userId, odd.ordDtlNo, odd.ordDate, odd.deliNo, odd.deliStatus, odd.deliAmt, odd.phone, odd.addrNo, odd.addr, odd.addrDtl, odd.size, odd.color, odd.buyAmt, odd.count, p.brandNo, p.brandNm, p.pdNo, p.pdName, pi.fileNm) " +
            "from OrderDtl odd " +
            "inner join OrderInfo oi on odd.orderInfo = oi " +
            "left join PdInfo p on odd.pdInfo = p " +
            "left join p.fileList pi " +
            "where oi.member.userId = :userId " +
            "and pi.fileOrd = 0" +
            "order by odd.ordDate desc ")
    Page<OrderDtlDTO> selectOrderDtlDTOByUserId(Pageable pageable,
                                   @Param("userId") String userId);

    //브랜드 주문 조회
    @Query("select new com.gywangsa.dto.OrderDtlDTO(oi.member.userId, odd.ordDtlNo, odd.ordDate, odd.deliNo, odd.deliStatus, odd.deliAmt, odd.phone, odd.addrNo, odd.addr, odd.addrDtl, odd.size, odd.color, odd.buyAmt, odd.count, p.brandNo, p.brandNm, p.pdNo, p.pdName, pi.fileNm) " +
            "from OrderDtl odd " +
            "inner join OrderInfo oi on odd.orderInfo = oi " +
            "left join PdInfo p on odd.pdInfo = p " +
            "left join p.fileList pi " +
            "where p.brandNo = :brandNo " +
            "and pi.fileOrd = 0" +
            "order by odd.ordDate desc ")
    Page<OrderDtlDTO> selectOrderDtlDTOByBrand(Pageable pageable, @Param("brandNo") Long brandNo);

    //브랜드 주문 상태 조회
    @Query("select new com.gywangsa.dto.OrderDtlDTO(oi.member.userId, odd.ordDtlNo, odd.ordDate, odd.deliNo, odd.deliStatus, odd.deliAmt, odd.phone, odd.addrNo, odd.addr, odd.addrDtl, odd.size, odd.color, odd.buyAmt, odd.count, p.brandNo, p.brandNm, p.pdNo, p.pdName, pi.fileNm) " +
            "from OrderDtl odd " +
            "inner join OrderInfo oi on odd.orderInfo = oi " +
            "left join PdInfo p on odd.pdInfo = p " +
            "left join p.fileList pi " +
            "where p.brandNo = :brandNo " +
            "and odd.deliStatus = :deliStatus " +
            "and pi.fileOrd = 0" +
            "order by odd.ordDate desc ")
    Page<OrderDtlDTO> selectBrandOrderDeliStatus(Pageable pageable, @Param("brandNo") Long brandNo, @Param("deliStatus") String deliStatus);


    //회원 삭제에 필요한 주문 리스트 조회
    @Query("select odd from OrderDtl odd where odd.orderInfo = :orderInfo")
    List<OrderDtl> selectOrderDtlDTOByRemoveUserId(@Param("orderInfo") OrderInfo orderInfo);
}
