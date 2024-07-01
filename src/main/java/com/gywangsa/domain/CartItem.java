package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"pdInfo","cart"})
@Table(name = "GYU_CART_ITEM",
        indexes = {@Index(name = "idx_cartItem_cart",columnList = "cart_no"),
                @Index(name = "idx_cartItem_pd_no",columnList = "pd_no")}
)
public class CartItem {

    @Id
    @Column(name="cart_item_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemNo;

    @Column(name = "pd_size")
    private String size;

    @Column(name = "pd_color")
    private String color;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "pd_no")
    private PdInfo pdInfo;

    @ManyToOne
    @JoinColumn(name = "cart_no")
    private Cart cart;

    public void changeCount(int count) {
        this.count = count;
    }
}
