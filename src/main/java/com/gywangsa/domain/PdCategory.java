package com.gywangsa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "GYU_PD_CATEGORY")
@SequenceGenerator(
        name = "gyu_pd_category_category_no_seq_gen",
        sequenceName = "gyu_pd_category_category_no_seq",
        initialValue = 1,
        allocationSize = 1
)
public class PdCategory {
    @Id
    @Column(name = "category_no")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gyu_pd_category_category_no_seq_gen"
    )
    private int categoryNo;//카테고리 번호

    @Column(name = "category_nm")
    private String categoryNm; //카테고리 이름

    @OneToMany(mappedBy = "pdCategory", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Builder.Default
    private List<PdItem> pdItemList = new ArrayList<>();

}
