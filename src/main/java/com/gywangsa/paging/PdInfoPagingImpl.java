package com.gywangsa.paging;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.domain.QPdFile;
import com.gywangsa.domain.QPdInfo;
import com.gywangsa.dto.PageRequestDTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class PdInfoPagingImpl extends QuerydslRepositorySupport implements PdInfoPaging {

    public PdInfoPagingImpl() {
        super(PdInfo.class);
    }

    @Override
    public Page<PdInfo> selectListByPdInfo(PageRequestDTO pageRequestDTO,Long categoryNo, Long itemNo){

        log.info("..................selectListByPdInfo..................");

        //페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("pdNo").descending());

        //객체 생성
        QPdInfo pdInfo = QPdInfo.pdInfo;
        QPdFile pdFile = QPdFile.pdFile;

        //from 절
        JPQLQuery<PdInfo> query = from(pdInfo);

        //Join 절
        query.leftJoin(pdInfo.fileList,pdFile);

        //where 절
        query.where(pdInfo.categoryNo.eq(categoryNo).and(pdInfo.itemNo.eq(itemNo).and(pdFile.fileOrd.eq(0))));


        this.getQuerydsl().applyPagination(pageable, query);

        List<PdInfo> pdInfoList = query.fetch();//목록 데이터 가져오기
        //List<Tuple> pdInfoList = query.select(pdInfo,pdFile).fetch();//목록 데이터 가져오기
        long total = query.fetchCount(); // 카운트

        log.info(pdInfoList);

        return new PageImpl<>(pdInfoList, pageable, total);
    }
}
