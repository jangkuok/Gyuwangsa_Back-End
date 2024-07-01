package com.gywangsa.service.Impl;

import com.gywangsa.domain.Cart;
import com.gywangsa.domain.CartItem;
import com.gywangsa.domain.Member;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.CartItemDTO;
import com.gywangsa.dto.CartItemListDTO;
import com.gywangsa.repository.CartItemRepository;
import com.gywangsa.repository.CartRepository;
import com.gywangsa.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    //특정 아이디 장바구니 존재 확인
    private Cart selectCart(String userId){
        //해당 아이디의 장바구니가 있는지 확인
        
        //없으면 Cart 생성하고 상품 추가
        Optional<Cart> result = cartRepository.selectCartMember(userId);
        
        Cart cart = null;
        if(result.isEmpty()){
            log.info("---------------------CartServiceImpl------------------------");
            log.info("-----------------------장바구니 생성--------------------------");
            Member member = Member.builder().userId(userId).build();
            Cart tempCart = Cart.builder()
                    .member(member)
                    .build();

            cart = cartRepository.save(tempCart);
        }else{
            cart = result.get();
        }

        return cart;
    }

    //카트 추가 or 수정
    @Override
    public List<CartItemListDTO> addCartOrModifyCart(CartItemDTO cartItemDTO) {

        log.info("---------------------addCartOrModifyCart-----------------------");
        String userId = cartItemDTO.getUserId();
        Long pdNo = cartItemDTO.getPdNo();
        String size = cartItemDTO.getSize();
        String color = cartItemDTO.getColor();
        int count = cartItemDTO.getCount();
        Long cartItemNo = cartItemDTO.getCartItemNo();

        CartItem ciDto = cartItemRepository.selectItemPdNo(userId,pdNo,color,size);

        //상품에서 장바구니 추가
        if(ciDto != null){
            log.info("---------------------상품에서 장바구니 추가(상품이 있을 경우)-----------------------");
            Optional<CartItem> cartItemResult = cartItemRepository.findById(ciDto.getCartItemNo());

            CartItem cartItem = cartItemResult.orElseThrow();

            cartItem.changeCount(cartItem.getCount() + count);

            cartItemRepository.save(cartItem);

            return selectCartItems(userId);
        }

        //장바구니에 상품이 있을 경우
        if(cartItemNo != null){
            log.info("---------------------상품이 있을 경우-----------------------");
            Optional<CartItem> cartItemResult = cartItemRepository.findById(cartItemNo);

            CartItem cartItem = cartItemResult.orElseThrow();

            cartItem.changeCount(count);

            cartItemRepository.save(cartItem);

            return selectCartItems(userId);
        }


        log.info("---------------------상품이 없을 경우-----------------------");
        Cart cart = selectCart(userId);

        CartItem cartItem = null;
        cartItemRepository.selectItemPdNo(userId,pdNo,color,size);

        if(cartItem == null){
            PdInfo pdInfo = PdInfo.builder().pdNo(pdNo).build();
            cartItem = CartItem.builder()
                    .cart(cart)
                    .pdInfo(pdInfo)
                    .count(count)
                    .size(size)
                    .color(color)
                    .build();
        }else{
            cartItem.changeCount(count);
        }
        log.info("-------------------장바구니 상품 저장-----------------------");
        cartItemRepository.save(cartItem);

        return selectCartItems(userId);
    }

    //특정 회원 카트 조회
    @Override
    public List<CartItemListDTO> selectCartItems(String userId) {
        log.info("---------------------특정 회원 카트 조회-----------------------");
        return cartItemRepository.selectItemCartDTObyUserID(userId);
    }

    //카트 상품 삭제
    @Override
    public List<CartItemListDTO> removeCartItem(Long cartItemNo) {
        log.info("----------------------카트 상품 삭제-------------------------");
        Long cartNo = cartItemRepository.selectCartFromItem(cartItemNo);

        cartItemRepository.deleteById(cartItemNo);

        return cartItemRepository.selectItemOfCartDTOByCart(cartNo);
    }
}
