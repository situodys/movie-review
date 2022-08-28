package com.example.mreview.domain.movie.repository;

import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movie.dto.MovieInfoDetailDTO;
import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import com.example.mreview.domain.movie.entity.MovieListInfo;
import com.example.mreview.global.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface MovieCustomRepository {
    Page<MovieListInfoDTO> getListPage(PageRequestDTO pageRequestDTOo);
    Long getTotalCount();

    MovieInfoDetailDTO findMovieDetail(Long id);
    Movie findMovieById(Long id);
    }
