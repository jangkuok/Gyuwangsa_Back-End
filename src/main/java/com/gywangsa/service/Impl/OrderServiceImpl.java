package com.gywangsa.service.Impl;

import com.gywangsa.domain.*;
import com.gywangsa.dto.OrderDtlDTO;
import com.gywangsa.dto.PageRequestDTO;
import com.gywangsa.dto.PageResponseDTO;
import com.gywangsa.dto.PdInfoDTO;
import com.gywangsa.repository.*;
import com.gywangsa.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderInfoRepository orderInfoRepository;

    private final OrderDtlRepository orderDtlRepository;

    private final MemberRepository memberRepository;

    private final PdInfoRepository pdInfoRepository;

    private final BrandRepository brandRepository;

    private LocalDateTime now = LocalDateTime.now();

    private Pageable getPageable(PageRequestDTO pageRequestDTO){
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("ordDate").descending());
        return  pageable;
    }

    private List<OrderDtlDTO> getPdInfoDTOList(Page<OrderDtlDTO> result){

        List<OrderDtlDTO> dtoList = result.get().map( order ->
                OrderDtlDTO.builder()
                        .userId(order.getUserId())
                        .ordDtlNo(order.getOrdDtlNo())
                        .ordDate(order.getOrdDate())
                        .deliNo(order.getDeliNo())
                        .deliStatus(order.getDeliStatus())
                        .deliAmt(order.getDeliAmt())
                        .phone(order.getPhone())
                        .addrNo(order.getAddrNo())
                        .addr(order.getAddr())
                        .addrDtl(order.getAddrDtl())
                        .size(order.getSize())
                        .color(order.getColor())
                        .buyAmt(order.getBuyAmt())
                        .count(order.getCount())
                        .brandNo(order.getBrandNo())
                        .brandNm(order.getBrandNm())
                        .pdNo(order.getPdNo())
                        .pdName(order.getPdName())
                        .imageFile(order.getImageFile())
                        .build()).collect(Collectors.toList());

        return dtoList;
    }

    //주문번호 생성
    private String makeDeliNo(){
        log.info("-------------------OrderServiceImpl-------------------");
        log.info("============주문번호 생성============");
        
        Random random = new Random();

        int randomStrLen = 10;

        StringBuffer randomBuf = new StringBuffer();

        for (int i = 0; i < randomStrLen; i++) {
            if (random.nextBoolean()) {
                randomBuf.append((char)((int)(random.nextInt(26)) + 97));
            } else {
                randomBuf.append(random.nextInt(10));
            }
        }
        String randomStr = randomBuf.toString();

        return randomStr;
    }


    //상품 주문 추가
    @Override
    public OrderDtlDTO addOrder(OrderDtlDTO orderDtlDTO) {

        log.info("-------------------OrderServiceImpl-------------------");
        log.info("============상품 주문 추가============");
        String userId = orderDtlDTO.getUserId();
        Long pdNo = orderDtlDTO.getPdNo();
        Long brandNo = orderDtlDTO.getBrandNo();

        Optional<OrderInfo> result = orderInfoRepository.selectOrderInfoMember(userId);

        Member member = memberRepository.selectMemberId(userId);

        OrderInfo orderInfo = null;

        //없으면 주문 생성
        if(result.isEmpty()){
            log.info("-------------------------회원 주문 추가-----------------------------");
            OrderInfo temp = OrderInfo.builder()
                    .member(member)
                    .build();

            orderInfo = orderInfoRepository.save(temp);
        }else{
            orderInfo = result.get();
        }

        log.info("-------------------------상품 추가 추가-----------------------------");
        Optional<PdInfo> result2 = pdInfoRepository.selectPdInfoByPdNo(pdNo);

        PdInfo pdInfo = result2.orElseThrow();

        Brand brand = brandRepository.selectBrandByBrandNo(brandNo);

        OrderDtl orderDtl = OrderDtl.builder()
                .ordDate(now)
                .deliNo(orderDtlDTO.getDeliNo())
                .deliStatus("결제 완료")
                .phone(orderDtlDTO.getPhone())
                .addrNo(orderDtlDTO.getAddrNo())
                .addr(orderDtlDTO.getAddr())
                .addrDtl(orderDtlDTO.getAddrDtl())
                .buyAmt(pdInfo.getBuyAmt())
                .size(orderDtlDTO.getSize())
                .color(orderDtlDTO.getColor())
                .deliAmt(orderDtlDTO.getDeliAmt())
                .count(orderDtlDTO.getCount())
                .brand(brand)
                .pdInfo(pdInfo)
                .orderInfo(orderInfo)
                .build();

        orderDtlRepository.save(orderDtl);
        return null;
    }

    //회원 주문 조회
    @Override
    public PageResponseDTO<OrderDtlDTO> selectListByOrderMember(PageRequestDTO pageRequestDTO, String userId) {

        log.info("-------------------OrderServiceImpl-------------------");
        log.info("============회원 주문 목록 조회============");

        Pageable pageable = getPageable(pageRequestDTO);

        log.info(pageable);
        log.info(userId);

        Page<OrderDtlDTO> result = orderDtlRepository.selectOrderDtlDTOByUserId(pageable,userId);

//        List<OrderDtlDTO> dtoList = result.get().map(order ->
//
//                OrderDtlDTO.builder()
//                        .userId(userId)
//                        .ordDtlNo(order.getOrdDtlNo())
//                        .ordDate(order.getOrdDate())
//                        .deliNo(order.getDeliNo())
//                        .deliStatus(order.getDeliStatus())
//                        .deliAmt(order.getDeliAmt())
//                        .phone(order.getPhone())
//                        .addrNo(order.getAddrNo())
//                        .addr(order.getAddr())
//                        .addrDtl(order.getAddrDtl())
//                        .size(order.getSize())
//                        .color(order.getColor())
//                        .buyAmt(order.getBuyAmt())
//                        .count(order.getCount())
//                        .brandNo(order.getBrandNo())
//                        .brandNm(order.getBrandNm())
//                        .pdNo(order.getPdNo())
//                        .pdName(order.getPdName())
//                        .imageFile(order.getImageFile())
//                        .build()).collect(Collectors.toList());

        List<OrderDtlDTO> dtoList =  getPdInfoDTOList(result);

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<OrderDtlDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //브랜드 주문 목록
    @Override
    public PageResponseDTO<OrderDtlDTO> selectBrandOrderList(PageRequestDTO pageRequestDTO, Long brandNo) {

        Pageable pageable = getPageable(pageRequestDTO);

        Page<OrderDtlDTO> result = orderDtlRepository.selectOrderDtlDTOByBrand(pageable,brandNo);

        List<OrderDtlDTO> dtoList =  getPdInfoDTOList(result);

        long totalCount = result.getTotalElements();


        return PageResponseDTO.<OrderDtlDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //브랜드 주문 상태 목록
    @Override
    public PageResponseDTO<OrderDtlDTO> selectBrandOrderDeliStatus(PageRequestDTO pageRequestDTO, Long brandNo, String deliStatus) {
        Pageable pageable = getPageable(pageRequestDTO);

        Page<OrderDtlDTO> result = orderDtlRepository.selectBrandOrderDeliStatus(pageable,brandNo,deliStatus);

        List<OrderDtlDTO> dtoList =  getPdInfoDTOList(result);

        long totalCount = result.getTotalElements();


        return PageResponseDTO.<OrderDtlDTO>pageResponseDTOMethod()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    //상품 취소
    @Override
    public void removeOrder(Long ordDtlNo, String deliStatus) {
        log.info("-------------------OrderServiceImpl-------------------");
        log.info("============회원 주문 취소============");
        log.info(ordDtlNo);
        log.info(deliStatus);
        orderDtlRepository.removeOrder(ordDtlNo, deliStatus);
    }
}

