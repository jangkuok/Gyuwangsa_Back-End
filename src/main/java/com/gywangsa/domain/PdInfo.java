package com.gywangsa.domain;

import com.gywangsa.pk.PdInfoPk;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "fileList")
@Table(name = "GYU_PD_INFO")
@IdClass(PdInfoPk.class)
@SequenceGenerator(
        name = "gyu_pd_info_pdNo_seq_gen",
        sequenceName = "gyu_pd_info_pdNo_seq",
        initialValue = 1,
        allocationSize = 1
)
public class PdInfo {
    @Id
    @Column(name = "category_no")
    private Long categoryNo; //카테고리 번호

    @Id
    @Column(name = "item_no")
    private Long itemNo; //중분류 번호

    @Id
    @Column(name = "pd_no")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gyu_pd_info_pdNo_seq_gen"
    )
    private Long pdNo; //상품 번호

    @Id
    @Column(name = "brand_no")
    private Long brandNo; //브랜드 번호

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate; //유효시작일

    @Column(name = "pd_name", length = 300)
    private String pdName; //상품 이름

    @Column(name = "brand_nm", length = 300)
    private String brandNm; //브랜드 이름

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
    @CollectionTable(name = "gyu_pd_file")
    @Builder.Default
    private List<PdFile> fileList = new ArrayList<>();


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
}
