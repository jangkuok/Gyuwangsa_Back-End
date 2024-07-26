package com.gywangsa.repository;

import com.gywangsa.domain.Cart;
import com.gywangsa.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select cart from Cart cart where cart.member.userId = :userId")
    Optional<Cart> selectCartMember(@Param("userId") String userId);

}
