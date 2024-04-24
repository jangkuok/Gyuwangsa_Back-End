package com.gywangsa.paging;

import com.gywangsa.domain.PdInfo;
import com.gywangsa.domain.QPdInfo;
import com.querydsl.jpa.JPQLOps;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class PdInfoPagingImpl extends QuerydslRepositorySupport implements PdInfoPaging {

    public PdInfoPagingImpl() {
        super(PdInfo.class);
    }

    @Override
    public Page<PdInfo> search() {

        log.info("PdInfoPaging..................");

        //객체 생성
        QPdInfo pdInfo = QPdInfo.pdInfo;

        //from 절
        JPQLQuery<PdInfo> query = from(pdInfo);

        //where 절
        query.where(pdInfo.pdName.contains("2"));

        //페이징 처리
        Pageable pageable = PageRequest.of(1,10, Sort.by("pdNo").descending());
        this.getQuerydsl().applyPagination(pageable, query);

        query.fetch();//목록 데이터 가져오기
        query.fetchCount(); // 카운트

        return null;
    }
}
