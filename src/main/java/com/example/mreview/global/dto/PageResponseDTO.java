package com.example.mreview.global.dto;

import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO {

    private List<MovieListInfoDTO> dtoList;

    private int totalPage;
    private int page;
    private int size;

    private int start,end;

    private boolean prev,next;

    private Long lastMno;

    private List<Integer> pageList;

    public PageResponseDTO(List<MovieListInfoDTO> result,PageRequestDTO pageRequestDTO, int totalPage) {
        dtoList=result;
        this.totalPage =totalPage;
        makePageList(pageRequestDTO);
        this.size = result.size();
    }

    private void makePageList(PageRequestDTO pageRequestDTO) {
        this.page=pageRequestDTO.getPage()+1;


        int tempEnd=(int)(Math.ceil(page/10.0))*10;

        start= tempEnd-9;

        prev=start>1;

        end=this.totalPage>tempEnd ? tempEnd : this.totalPage;

        next=totalPage>tempEnd;

        pageList= IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

        lastMno = dtoList.get(this.size).getMovie().getMno();
    }

}
