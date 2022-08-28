package com.example.mreview.global.dto;

import lombok.*;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageRequestDTO implements Serializable {

    @Min(value =0L)
    private Integer page;
    @Min(value =1L)
    private Integer size;

    private Long totalCount;
    private String title;

    public Integer getPage() {
        return page == null ? 0 : page;
    }

    public Integer getSize() {
        return size == null ? 10 : size;
    }

    public Long getTotalCount() {
        return totalCount == null ? -1L : totalCount;
    }

    public String getTitle() {
        return title;
    }
}
