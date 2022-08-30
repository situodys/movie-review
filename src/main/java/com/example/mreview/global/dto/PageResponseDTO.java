package com.example.mreview.global.dto;

import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO implements Serializable {

    private List<MovieListInfoDTO> contents;

    private int totalPage;
    private long totalResultsCount;
    private int curPage;
    private int pageSize;

    private int startIndex,endIndex;

    private boolean prev,next;

    private List<Integer> pageList;

    @Builder
    public PageResponseDTO(Page<MovieListInfoDTO> result, PageRequestDTO pageRequestDTO) {
        this.contents=result.getContent();
        this.totalPage = result.getTotalPages();
        this.totalResultsCount = result.getTotalElements();
        this.curPage=pageRequestDTO.getPage()+1;
        this.pageSize = pageRequestDTO.getSize();
        makePageList();
    }

    private void makePageList() {
        this.curPage = curPage;
        this.totalResultsCount = totalResultsCount;
        int tempEnd=(int)(Math.ceil(curPage/10.0))*10;

        startIndex= tempEnd-9;

        prev=startIndex>1;

        endIndex=this.totalPage>tempEnd ? tempEnd : this.totalPage;

        next=totalPage>tempEnd;

        pageList= IntStream.rangeClosed(startIndex,endIndex).boxed().collect(Collectors.toList());
    }

}
