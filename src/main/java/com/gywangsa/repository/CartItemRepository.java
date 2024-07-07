package com.gywangsa.repository;

import com.gywangsa.domain.CartItem;
import com.gywangsa.dto.CartItemListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    //특정 사용자의 모든 장바구니 가져올 경우
    @Query("select new com.gywangsa.dto.CartItemListDTO(ci.cartItemNo, ci.count, p.brandNo, p.brandNm, ci.size, ci.color, p.pdNo, p.pdName, p.buyAmt, pi.fileNm) " +
            "from CartItem ci " +
            "inner join Cart c on ci.cart = c " +
            "left join PdInfo p on ci.pdInfo = p " +
            "left join p.fileList pi " +
            "where pi.fileOrd = 0 " +
            "and c.member.userId = :userId " +
            "order by ci.cartItemNo desc")
    List<CartItemListDTO> selectItemCartDTObyUserID(@Param("userId") String userId);

    //아이디, 상품 번호, 색상, 사이즈로  장바구니 아이템 존재 확인
    @Query("select ci from CartItem ci left join Cart c on ci.cart = c where c.member.userId = :userId and ci.pdInfo.pdNo = :pdNo and ci.color = :color and ci.size = :size")
    CartItem selectItemPdNo(@Param("userId") String userId, @Param("pdNo") Long pdNo, @Param("color") String color, @Param("size") String size);
    //<CartItem> selectItemPdNo(@Param("userId") String userId, @Param("pdNo") Long pdNo, @Param("color") String color, @Param("size") String size);



    //장바구니 아이템 번호로 장바구니 정보 확인
    @Query("select c.cartNo from Cart c left join CartItem ci on ci.cart = c where ci.cartItemNo = :cartItemNo")
    Long selectCartFromItem(@Param("cartItemNo") Long cartItemNo);

    @Query("select new com.gywangsa.dto.CartItemListDTO(ci.cartItemNo, ci.count, p.brandNo, p.brandNm, ci.size, ci.color, p.pdNo, p.pdName, p.buyAmt, pi.fileNm) " +
            "from CartItem ci " +
            "inner join Cart c on ci.cart = c " +
            "left join PdInfo p on ci.pdInfo = p " +
            "left join p.fileList pi " +
            "where pi.fileOrd = 0 " +
            "and ci.cartItemNo = :cartItemNo " +
            "order by ci.cartItemNo desc")
    CartItemListDTO selectItemCartDTObyCartItemNo(@Param("cartItemNo") Long cartItemNo);

    //장바구니 아이템 번호로 모든 장바구니 조회
    @Query("select new com.gywangsa.dto.CartItemListDTO(ci.cartItemNo, ci.count, p.brandNo, p.brandNm, ci.size, ci.color, p.pdNo, p.pdName, p.buyAmt, pi.fileNm) " +
            "from CartItem ci " +
            "inner join Cart c on ci.cart = c " +
            "left join PdInfo p on ci.pdInfo = p " +
            "left join p.fileList pi " +
            "where pi.fileOrd = 0 " +
            "and c.cartNo = :cartNo " +
            "order by ci.cartItemNo desc")
    List<CartItemListDTO> selectItemOfCartDTOByCart(@Param("cartNo") Long cartNo);

}
