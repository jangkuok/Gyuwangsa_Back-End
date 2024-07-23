package com.gywangsa.controller;

import com.gywangsa.dto.CartItemDTO;
import com.gywangsa.dto.CartItemListDTO;
import com.gywangsa.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    //카트 수량 체크
    //@PreAuthorize("#cartItemDTO.userId == authentication.name")
    @PostMapping("/changeCart")
    public List<CartItemListDTO> changeCart(@RequestPart(value = "cartItemDTO") List<CartItemDTO> cartItemDTO) {
        List<CartItemListDTO> list = new ArrayList<>();

        for (int i = 0; i < cartItemDTO.size(); i++) {
            if (cartItemDTO.get(i).getCount() <= 0) {
                log.info("-------------------삭제----------------------");
                return cartService.removeCartItem(cartItemDTO.get(i).getCartItemNo());
            }
            log.info("-------------------추가/수정----------------------");

            list = cartService.addCartOrModifyCart(cartItemDTO.get(i));
        }
        return list;
    }

    //카트 수량 체크
    //@PreAuthorize("#cartItemDTO.userId == authentication.name")
    @PostMapping("/changeCartPage")
        public List<CartItemListDTO> changeCart(@RequestBody CartItemDTO cartItemDTO){

        log.info("-----------------CartController-----------------");
        log.info("-------------------changeCart-------------------");
        log.info(cartItemDTO);

        if(cartItemDTO.getCount() <= 0){
            log.info("-------------------삭제----------------------");
            return cartService.removeCartItem(cartItemDTO.getCartItemNo());
        }
        log.info("-------------------추가/수정----------------------");
        return cartService.addCartOrModifyCart(cartItemDTO);

    }

    //카트 리스트
    //@PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/items")
    public List<CartItemListDTO> selectCartItems(Principal principal) {
        String userId = principal.getName();
        log.info("-------------------selectCartItems----------------------");
        log.info("-------------------userId" + userId + "----------------------");
        return cartService.selectCartItems(userId);
    }

    
    //카트 삭제
    @DeleteMapping("/removeItem/{cartItemNo}")
    public List<CartItemListDTO> removeCart(@PathVariable("cartItemNo") Long cartItemNo) {
        log.info("-------------------removeCart----------------------");
        log.info(cartItemNo);
        return cartService.removeCartItem(cartItemNo);
    }

    //카트 삭제
    @PutMapping("/removeCartList")
    public void removeCartList(@RequestBody Map<String, Object> cartItemNo) {
        log.info("-------------------removeCartList----------------------");

        for(String key : cartItemNo.keySet()){
            cartService.removeCartItem(Long.valueOf(String.valueOf(cartItemNo.get(key))));
        }
    }
}
