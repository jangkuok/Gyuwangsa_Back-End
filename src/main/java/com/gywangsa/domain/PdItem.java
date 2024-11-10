package com.gywangsa.domain;

import com.gywangsa.dto.PdCategoryDTO;
import com.gywangsa.dto.PdItemDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GYU_PD_ITEM")
@SequenceGenerator(
        name = "gyu_pd_item_item_no_seq_gen",
        sequenceName = "gyu_pd_item_item_no_seq",
        initialValue = 1,
        allocationSize = 1
)
public class PdItem {
    @Id
    @Column(name = "item_no")
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "gyu_pd_item_item_no_seq_gen"
//    )
    private int itemNo;//아이템 번호

    @Column(name = "item_Nm")
    private String itemNm;//아이템 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_no")
    @ToString.Exclude
    private PdCategory pdCategory;

    public PdItemDTO addPdItemDTO(PdItem pdItem){
        PdItemDTO pdItemDTO = PdItemDTO.builder()
                .itemNo(pdItem.getItemNo())
                .itemNm(pdItem.getItemNm())
                .build();
        return pdItemDTO;
    }


}
