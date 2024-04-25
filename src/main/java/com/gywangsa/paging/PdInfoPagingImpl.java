package com.gywangsa.paging;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.domain.QPdInfo;
import com.gywangsa.dto.PageRequestDTO;
import com.querydsl.jpa.JPQLOps;
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
    public Page<PdInfo> selectListByPdInfo(PageRequestDTO pageRequestDTO){

        log.info("selectListByPdInfo..................");

        //객체 생성
        QPdInfo pdInfo = QPdInfo.pdInfo;

        //from 절
        JPQLQuery<PdInfo> query = from(pdInfo);

        //where 절
        //query.where(pdInfo.pdName.contains("2"));

        //페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("pdNo").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        List<PdInfo> pdInfoList = query.fetch();//목록 데이터 가져오기
        long total = query.fetchCount(); // 카운트

        return new PageImpl<>(pdInfoList, pageable, total);
    }
}
