package com.gywangsa.service;


import com.gywangsa.domain.OrderDtl;
import com.gywangsa.dto.OrderDtlDTO;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    //상품 주문
    OrderDtlDTO addOrder(OrderDtlDTO orderDtlDTO);

    //상품 취소
    void removeOrder(Long ordDtlNo, String deliStatus);

    PageResponseDTO<OrderDtlDTO> selectListByOrderMember(PageRequestDTO pageRequestDTO, String userId);

    default OrderDtlDTO entityOrder(OrderDtl orderDtl,String userId) {
        OrderDtlDTO orderDtlDTO = OrderDtlDTO.builder()
                                .userId(userId)
                                .ordDtlNo(orderDtl.getOrdDtlNo())
                                .ordDate(orderDtl.getOrdDate())
                                .deliNo(orderDtl.getDeliNo())
                                .deliStatus(orderDtl.getDeliStatus())
                                .phone(orderDtl.getPhone())
                                .addrNo(orderDtl.getAddrNo())
                                .addr(orderDtl.getAddr())
                                .addrDtl(orderDtl.getAddrDtl())
                                .size(orderDtl.getSize())
                                .color(orderDtl.getColor())
                                .buyAmt(orderDtl.getBuyAmt())
                                .count(orderDtl.getCount())
                                .brandNo(orderDtl.getPdInfo().getBrandNo())
                                .brandNm(orderDtl.getPdInfo().getBrandNm())
                                .pdNo(orderDtl.getPdInfo().getPdNo())
                                .pdName(orderDtl.getPdInfo().getPdName())
                                .imageFile(orderDtl.getPdInfo().getFileList().get(0).getFileNm())
                                .build();

        return orderDtlDTO;
    }
}
