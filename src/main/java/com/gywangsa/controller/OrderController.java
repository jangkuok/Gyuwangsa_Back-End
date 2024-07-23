package com.gywangsa.controller;

import com.gywangsa.dto.*;
import com.gywangsa.service.CartService;
import com.gywangsa.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    private final CartService cartService;


    //주문 목록
    @PostMapping("/orderPageLoad")
        public List<CartItemListDTO> orderPageLoad(@RequestBody Map<String, Object> cartItemNo) {
        log.info("-----------------OrderController-----------------");
        log.info("-------------------상품 이동-------------------");

        List<CartItemListDTO> cartItemDTOList = new ArrayList<>();

        for (String key: cartItemNo.keySet()){
            CartItemListDTO cartItemListDTO = cartService.selectCartItemNo(Long.valueOf(String.valueOf(cartItemNo.get(key))));
            cartItemDTOList.add(cartItemListDTO);
            log.info(cartItemDTOList);
        }
        return cartItemDTOList;
    }

    //주문 추가
    @PostMapping("/addOrder")
    public List<OrderDtlDTO> addOrder(@RequestPart(value = "orderDtlDTO") List<OrderDtlDTO> orderDtlDTO) {

        log.info("-----------------OrderController-----------------");
        log.info("-------------------주문추가-------------------");


        log.info(orderDtlDTO);
        List<OrderDtlDTO> list = new ArrayList<>();

        for (int i = 0; i < orderDtlDTO.size(); i++){
            OrderDtlDTO dto = orderService.addOrder(orderDtlDTO.get(i));
            list.add(dto);
        }

        log.info(list);

        return list;
    }
    
    //특정 아이디 주문 목록
    @GetMapping("/orderList/{userId}")
    public PageResponseDTO<OrderDtlDTO> selectOrderListByUser(PageRequestDTO pageRequestDTO,
                                                              @PathVariable("userId") String userId){
        log.info("-------------------OrderController-------------------");
        log.info("============회원 주문 리스트 조회============");
        log.info(orderService.selectListByOrderMember(pageRequestDTO,userId));

        return orderService.selectListByOrderMember(pageRequestDTO,userId);
    }

    //배송 상태 변경
    @Transactional
    @PutMapping("/orderCancel/{ordDtlNo}/{deliState}")
    public Map<String, String> removeOrder(@PathVariable("ordDtlNo") Long ordDtlNo,
                                           @PathVariable("deliState") String deliState){
        log.info("-------------------OrderController-------------------");
        log.info("============회원 주문 취소============");

        orderService.removeOrder(ordDtlNo,deliState);

        return Map.of("result", deliState);
    }


}
