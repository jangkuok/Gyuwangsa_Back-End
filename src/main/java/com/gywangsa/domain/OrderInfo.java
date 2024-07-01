package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
@Table(name = "GYU_ORDER_INFO",
        indexes = {@Index(name = "idx_ord_userId",columnList = "userId")}
)
public class OrderInfo {

    @Id
    @Column(name="ord_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordNo;

    @OneToOne
    @JoinColumn(name = "userId")
    private  Member member;
}
