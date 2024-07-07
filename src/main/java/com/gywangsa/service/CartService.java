package com.gywangsa.service;


import com.gywangsa.domain.CartItem;
import com.gywangsa.dto.CartItemDTO;
import com.gywangsa.dto.CartItemListDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartService {

    //카트 추가 or 수정
    List<CartItemListDTO> addCartOrModifyCart(CartItemDTO cartItemDTO);

    //특정 회원 카트 조회
    List<CartItemListDTO> selectCartItems(String userId);

    //특정 번호 카트 조회
    CartItemListDTO selectCartItemNo(Long cartItemNo);

    //카트 상품 삭제
    List <CartItemListDTO> removeCartItem(Long cartItemNo);

}
