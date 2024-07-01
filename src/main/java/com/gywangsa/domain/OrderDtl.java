package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"pdInfo","orderInfo","brand"})
@Table(name = "GYU_ORDER_INFO",
        indexes = {@Index(name = "idx_orderInfo_orderInfo",columnList = "ordNo"),
                @Index(name = "idx_orderInfo_pd_no",columnList = "pd_no"),
                @Index(name = "idx_orderInfo_brand_cd",columnList = "brand_cd")}
)
public class OrderDtl {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordDtlNo;

    @Column(name = "ord_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ordDate;

    @Column(name = "deli_no")
    private String deliNo;

    @Column(name = "deli_status")
    private String deliStatus;

    @Column(name = "phone")
    private String phone;

    @Column(name = "addr_no")
    private String addrNo;

    @Column(name = "addr")
    private String addr;

    @Column(name = "addr_dtl")
    private String addrDtl;

    @ManyToOne
    @JoinColumn(name = "pd_no")
    private PdInfo pdInfo;

    @ManyToOne
    @JoinColumn(name = "brand_cd")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "ordNo")
    private OrderInfo orderInfo;

}
