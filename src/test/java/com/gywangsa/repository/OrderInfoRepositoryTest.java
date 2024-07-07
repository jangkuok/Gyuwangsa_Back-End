package com.gywangsa.repository;

import com.gywangsa.domain.Member;
import com.gywangsa.domain.OrderDtl;
import com.gywangsa.domain.OrderInfo;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.OrderDtlDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class OrderInfoRepositoryTest {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderDtlRepository orderDtlRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PdInfoRepository pdInfoRepository;

    private LocalDateTime now = LocalDateTime.now();

    @Transactional
    @Commit
    @Test
    public void testInsert(){
        String userId = "test1";
        Long pdNo = 219L;
        String size = "M";
        String color = "네이비";
        int count = 1;

        Optional<OrderInfo> result = orderInfoRepository.selectOrderInfoMember(userId);

        Member member = memberRepository.selectMemberId(userId);

        OrderInfo orderInfo = null;

        if(result.isEmpty()){

            OrderInfo temp = OrderInfo.builder()
                    .member(member)
                    .build();

            orderInfo = orderInfoRepository.save(temp);
        }else{
            orderInfo = result.get();
        }

        Optional<PdInfo> result2 = pdInfoRepository.selectPdInfoByPdNo(pdNo);

        PdInfo pdInfo = result2.orElseThrow();

        OrderDtl orderDtl = OrderDtl.builder()
                .ordDate(now)
                .deliNo("02354864")
                .deliStatus("주문 완료")
                .phone(member.getPhone())
                .addrNo(member.getAddrNo())
                .addr(member.getAddr())
                .addrDtl(member.getAddrDtl())
                .buyAmt(pdInfo.getBuyAmt())
                .size(size)
                .color(color)
                .count(count)
                .pdInfo(pdInfo)
                .orderInfo(orderInfo)
                .build();

        orderDtlRepository.save(orderDtl);

    }

    @Test
    public void testListOfMember(){
        //List<OrderDtlDTO> orderDtlListDTO = orderDtlRepository.selectOrderDtlDTOByUserIdTest("test1");

        //log.info(orderDtlListDTO);
    }
}