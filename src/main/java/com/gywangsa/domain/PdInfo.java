package com.gywangsa.domain;

import com.gywangsa.pk.PdInfoPk;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"fileList", "sizeList"})
@Table(name = "GYU_PD_INFO")
@SequenceGenerator(
        name = "gyu_pd_info_pdNo_seq_gen",
        sequenceName = "gyu_pd_info_pdNo_seq",
        initialValue = 1,
        allocationSize = 1
)
public class PdInfo {

    @Id
    @Column(name = "pd_no")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gyu_pd_info_pdNo_seq_gen"
    )
    private Long pdNo; //상품 번호

    @Column(name = "category_no")
    private Long categoryNo; //카테고리 번호

    @Column(name = "item_no")
    private Long itemNo; //중분류 번호

    @Column(name = "brand_no")
    private Long brandNo; //브랜드 번호

    @Column(name = "brand_nm", length = 300)
    private String brandNm; //브랜드 이름

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate; //유효시작일

    @Column(name = "pd_name", length = 300)
    private String pdName; //상품 이름

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate; //유효종료일


    @Column(name = "buy_amt")
    private int buyAmt; //가격

    @Column(name = "like_cnt")
    private int likeCnt; //좋아요 갯수

    @Column(name = "sex_cd", length = 10)
    private String sexCd; //성별

    @Column(name = "note", length = 300, nullable = true)
    private String note; //설명

    @Column(name = "delFlag",columnDefinition = "boolean default false")
    private boolean delFlag; //삭제 상태

    @ElementCollection
    @OrderColumn(name = "id")
    @CollectionTable(name = "gyu_pd_file")
    @Builder.Default
    private List<PdFile> fileList = new ArrayList<>(); //이미지 리스트

    @ElementCollection
    @OrderColumn(name = "id")
    @CollectionTable(name = "gyu_pd_size")
    @Builder.Default
    private List<PdSize> sizeList = new ArrayList<>(); //사이즈 리스트


    public void changePdName(String pdName) {
        this.pdName = pdName;
    }

    public void changeEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void changeBuyAmt(int buyAmt) {
        this.buyAmt = buyAmt;
    }

    public void changeLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public void changeDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public void changeNote(String note) {
        this.note = note;
    }

    //파일 순번 값
    public void addFile(PdFile file){
        file.changeFileOrd(fileList.size());
        fileList.add(file);
    }

    //파일 등록
    public void addFileString(String name){
        PdFile file = PdFile.builder()
                .fileNm(name)
                .build();
        addFile(file);
    }

    //파일 삭제
    public void delFileList(){
        this.fileList.clear();
    }

    //사이즈 등록
    public void addSize(PdSize pdSize){
        PdSize size = PdSize.builder()
                .sizeType(pdSize.getSizeType())
                .pdType(pdSize.getPdType())
                .attr1(pdSize.getAttr1())
                .attr2(pdSize.getAttr2())
                .attr3(pdSize.getAttr3())
                .attr4(pdSize.getAttr4())
                .attr5(pdSize.getAttr5())
                .attr6(pdSize.getAttr6())
                .attr7(pdSize.getAttr7())
                .color(pdSize.getColor())
                .colorCode(pdSize.getColorCode())
                .sizeCnt(pdSize.getSizeCnt())
                .build();
        sizeList.add(size);
    }

    //사이즈 리스트 등록
    public void changeSizeList(List<PdSize> sizeList) {
        this.sizeList = sizeList;
    }

    //사이즈 리스트 삭제
    public void delSizeList(){
        this.sizeList.clear();
    }
}
