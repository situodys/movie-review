package com.example.mreview.global.dto;

import com.example.mreview.global.entity.SearchTypes;
import com.example.mreview.global.entity.SearchValue;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

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
