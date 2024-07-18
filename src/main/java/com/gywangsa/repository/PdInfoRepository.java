package com.gywangsa.repository;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.paging.PdInfoPaging;
import com.gywangsa.pk.PdInfoPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PdInfoRepository extends JpaRepository<PdInfo, Long>, PdInfoPaging {


    @EntityGraph(attributePaths = {"fileList","sizeList"})
    @Query("select p from PdInfo p where p.pdNo= :pdNo")
    Optional<PdInfo> selectPdInfoByPdNo(@Param("pdNo") Long pdNo);



    @Modifying
    @Query("update PdInfo p set p.pdName = :pdName, p.buyAmt = :buyAmt, p.note = :note where p.pdNo = :pdNo")
    void modifyPdInfoByPdNo(@Param("pdNo") Long pdNo,
                            @Param("pdName") String pdName,
                            @Param("buyAmt") int buyAmt,
                            @Param("note") String note);



    //삭제
    @Modifying
    @Query("update PdInfo p set p.delFlag = :delFlag where p.pdNo = :pdNo")
    void modifyToDelFlag(@Param("pdNo") Long pdNo,
                         @Param("delFlag") boolean delFlag);

    //완전 삭제
    @Transactional
    @Modifying
    @Query("delete from PdInfo p where p.pdNo = :pdNo")
    void removePdInfoByPdNo(@Param("pdNo") Long pdNo);

    //상품 리스트
    @Query("select p, fl from PdInfo p left join p.fileList fl where p.delFlag = false and p.categoryNo = :categoryNo and p.itemNo = :itemNo  and p.delFlag = false and fl.fileOrd = 0")
    Page<Object[]> selectListItemPdInfo(Pageable pageable,
                                        @Param("categoryNo") Long categoryNo,
                                        @Param("itemNo") Long itemNo);

    //특정 브랜드 상품 목록
    @Query("select p, fl from PdInfo p left join p.fileList fl where p.delFlag = false and p.brandNo = :brandNo and p.delFlag = false and fl.fileOrd = 0")
    Page<Object[]> selectListByBrandPdInfo(Pageable pageable,
                                           @Param("brandNo") Long brandNo);


    //특정 브랜드 상품 카테고리 목록
    @Query("select p, fl from PdInfo p left join p.fileList fl where p.delFlag = false and p.categoryNo = :categoryNo and p.brandNo = :brandNo and p.delFlag = false and fl.fileOrd = 0")
    Page<Object[]> selectListByBrandCategory(Pageable pageable,
                                           @Param("categoryNo") Long categoryNo,
                                           @Param("brandNo") Long brandNo);

    //특정 브랜드 상품 카테고리 아이템 목록
    @Query("select p, fl from PdInfo p left join p.fileList fl  where p.delFlag = false and p.categoryNo = :categoryNo and p.itemNo = :itemNo and p.brandNo = :brandNo and p.delFlag = false and fl.fileOrd = 0")
    Page<Object[]> selectListByBrandCategoryItem(Pageable pageable,
                                             @Param("categoryNo") Long categoryNo,
                                                 @Param("itemNo") Long itemNo,
                                             @Param("brandNo") Long brandNo);

    //키워드 검색
    @Query("select p, fl from PdInfo p left join p.fileList fl where p.delFlag = false and p.pdName like %:keyword% and p.delFlag = false and fl.fileOrd = 0")
    Page<Object[]> findByPdNameContaining(Pageable pageable,@Param("keyword") String keyword);

    //상품 좋아요 순위 검색
    @Query("select p from PdInfo p  where p.delFlag = false order by p.likeCnt desc")
    List<PdInfo> selectLikePdInfoRank();

    //상품 랜덤 목록
    @Query(value = "select * from gyu_pd_info where del_flag = false order by random() limit 15",nativeQuery = true)
    List<PdInfo> selectRandomPdList();


}
