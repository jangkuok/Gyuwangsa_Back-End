package com.gywangsa.repository;

import com.gywangsa.domain.Cart;
import com.gywangsa.domain.CartItem;
import com.gywangsa.domain.Member;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.CartItemListDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    @Commit
    @Test
    public void testInsert(){
        String userId = "test1";
        Long pdNo = 219L;
        String size = "M";
        String color = "네이비";
        int count = 1;

        //아이디/상품번호로 장바구니 확인
        //Optional<CartItem> opCartItem = cartItemRepository.selectItemPdNo(userId,pdNo,color,size);
        //CartItem cartItem = opCartItem.orElseThrow();

        CartItem cartItem = cartItemRepository.selectItemPdNo(userId,pdNo,color,size);

        if(cartItem != null){
            cartItem.changeCount(count);
            cartItemRepository.save(cartItem);
            return;
        }

        //사용자 장바구니에 아이템 저장
        Optional<Cart> result = cartRepository.selectCartMember(userId);
        Cart cart = null;

        //장바구니가 없을 경우
        if(result.isEmpty()){
            Member member = Member.builder().userId(userId).build();
            Cart tempCart = Cart.builder()
                    .member(member)
                    .build();

            cart = cartRepository.save(tempCart);
        }
        //장바구니가 존재하는데 상품이 안 담겨 있을 경우
        else {
            cart = result.get();
        }

        //장바구니 저장
        PdInfo pdInfo = PdInfo.builder()
                .pdNo(pdNo)
                .build();
        
        cartItem = CartItem.builder()
                .cart(cart)
                .pdInfo(pdInfo)
                .count(count)
                .size(size)
                .color(color)
                .build();

        cartItemRepository.save(cartItem);
    }

    //아이디 조회
    @Test
    public void testListOfMember(){

        String userId = "test1";

        List<CartItemListDTO> cartItemListDTO = cartItemRepository.selectItemCartDTObyUserID(userId);
        for(CartItemListDTO dto : cartItemListDTO){
            log.info(dto);
        }

    }

    //개수 수정
    @Transactional
    @Commit
    @Test
    public void testUpdateCount(){
        Long cartItemNo = 1L;
        int count = 5;
        Optional<CartItem> resultCartItem = cartItemRepository.findById(cartItemNo);

        CartItem cartItem = resultCartItem.orElseThrow();
        cartItem.changeCount(count);
        cartItemRepository.save(cartItem);
    }

    //장바구니 삭제
    @Transactional
    @Commit
    @Test
    public void testDeleteThenList(){
        Long cartItemNo = 1L;
        Long cartNo = cartItemRepository.selectCartFromItem(cartItemNo);
        cartItemRepository.deleteById(cartItemNo);

        List<CartItemListDTO> cartItemListDTO = cartItemRepository.selectItemOfCartDTOByCart(cartNo);
        for(CartItemListDTO dto : cartItemListDTO){
            log.info(dto);
        }
    }
}
