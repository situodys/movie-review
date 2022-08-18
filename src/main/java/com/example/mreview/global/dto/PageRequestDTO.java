package com.example.mreview.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    private Integer page;
    private Integer size;

    private Long totalCount;
    private String title;
    private String content;






}
