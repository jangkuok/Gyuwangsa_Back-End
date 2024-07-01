package com.gywangsa.domain;

import jakarta.persistence.*;


import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
@Table(name = "GYU_CART_INFO",
        indexes = {@Index(name = "idx_cart_userId",columnList = "userId")}
)
public class Cart {
    @Id
    @Column(name="cart_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartNo;

    @OneToOne
    @JoinColumn(name = "userId")
    private Member member;

}
