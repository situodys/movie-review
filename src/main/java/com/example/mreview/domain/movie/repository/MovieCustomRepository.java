package com.example.mreview.domain.movie.repository;

import com.example.mreview.global.dto.MovieListInfoDTO;
import com.example.mreview.global.dto.PageRequestDTO;
import com.example.mreview.global.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieCustomRepository {
    PageResponseDTO getListPage(PageRequestDTO pageRequestDTOo);
    Long getTotalCount();
}
