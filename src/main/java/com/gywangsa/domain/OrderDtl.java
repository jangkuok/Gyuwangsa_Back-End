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
@Table(name = "GYU_ORDER_DTL",
        indexes = {@Index(name = "idx_orderInfo_orderInfo",columnList = "ord_No"),
                @Index(name = "idx_orderInfo_pdInfo",columnList = "pd_no"),
                @Index(name = "idx_orderInfo_brand",columnList = "brand_no")}
)
public class OrderDtl {
    @Id
    @Column(name = "ord_dtl_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordDtlNo;

    @Column(name = "ord_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ordDate;

    @Column(name = "deli_no")
    private String deliNo;

    @Column(name = "deli_status")
    private String deliStatus;

    @Column(name = "deli_amt")
    private String deliAmt;

    @Column(name = "phone")
    private String phone;

    @Column(name = "addr_no")
    private String addrNo;

    @Column(name = "addr")
    private String addr;

    @Column(name = "addr_dtl")
    private String addrDtl;

    @Column(name = "buy_amt")
    private int buyAmt;

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
    @JoinColumn(name = "brand_no")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "ord_No")
    private OrderInfo orderInfo;

}
