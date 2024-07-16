package com.gywangsa.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name ="GYU_LIKE_CHK")
public class LikeChk {

    @Id
    @Column(name = "like_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeNo;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "pd_no")
    private Long pdNo;
}

