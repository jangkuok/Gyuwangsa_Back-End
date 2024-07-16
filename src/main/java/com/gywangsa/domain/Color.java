package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GYU_COLOR")

public class Color {
    @Id
    @Column(name="color_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colorNo;

    @Column(name="colorNm")
    private String colorNm;
}
