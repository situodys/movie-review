package com.example.mreview.domain.movie.repository;

import com.example.mreview.domain.movie.dto.MovieInfoDetailDTO;
import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import com.example.mreview.global.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieCustomRepository {
    Page<MovieListInfoDTO> getListPage(PageRequestDTO pageRequestDTOo);
    Long getTotalCount();

    List<MovieInfoDetailDTO> findMovieDetail(Long id);
}
