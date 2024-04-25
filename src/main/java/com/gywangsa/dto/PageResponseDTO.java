package com.gywangsa.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
//페이지 결과물
public class PageResponseDTO<E> {

    private List<E> dtoList; //목록 리스트

    private List<Integer> pageNumList; //front에 보내는 페이징 처리를 위한 숫자 설정

    private PageRequestDTO pageRequestDTO; //responseDTO 정보

    private boolean prev, next; //전 목록, 다음 목록

    private int totalCount, prevPage, nextPage, totalPage, current; //총 갯수, 이전 페이지, 다음 페이지, 전체 페이지, 현재 페이지

    @Builder(builderMethodName = "pageResponseDTOMethod")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total){
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int)total;

        //끝 페이지
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;

        //시작 페이지
        int start = end - 9;

        //목록에 마지막 페이지
        int last = (int)(Math.ceil(totalCount/(double)pageRequestDTO.getSize()));

        end = end > last ? last:end;

        this.prev = start>1;

        this.next = totalCount > pageRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start -1 : 0;

        this.nextPage = next ? end +1 : 0;

    }
}
