package com.gywangsa.repository;


import com.gywangsa.domain.Member;
import com.gywangsa.domain.PdInfo;
import com.gywangsa.dto.OrderDtlDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.service.OrderService;
import com.gywangsa.service.PdInfoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PdInfoService pdInfoService;

    @Transactional
    @Commit
    @Test
    public void addOrder(){

        Member member = memberRepository.selectMemberId("test2");
        PdInfoDTO pdInfo = pdInfoService.selectPdInfoByPdNo(220L);

        OrderDtlDTO orderDtlDTO = new OrderDtlDTO();
        orderDtlDTO.setUserId(member.getUserId());
        orderDtlDTO.setPhone(member.getPhone());
        orderDtlDTO.setAddrNo(member.getAddrNo());
        orderDtlDTO.setAddr(member.getAddr());
        orderDtlDTO.setAddrDtl(member.getAddrDtl());
        orderDtlDTO.setBuyAmt(pdInfo.getBuyAmt());
        orderDtlDTO.setSize("L");
        orderDtlDTO.setColor("네이버");
        orderDtlDTO.setCount(2);
        orderDtlDTO.setPdNo(pdInfo.getPdNo());
        orderDtlDTO.setPdName(pdInfo.getPdName());
        orderDtlDTO.setBrandNo(pdInfo.getBrandNo());
        orderDtlDTO.setBrandNm(pdInfo.getBrandNm());


        log.info(orderService.addOrder(orderDtlDTO));
    }

}
