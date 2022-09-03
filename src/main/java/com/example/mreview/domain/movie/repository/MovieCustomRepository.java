package com.example.mreview.domain.movie.repository;

import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movie.entity.MovieInfoDetail;
import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import com.example.mreview.global.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieCustomRepository {
    Page<MovieListInfoDTO> getListPage(PageRequestDTO pageRequestDTOo);

    Long getTotalCount();

    List<MovieInfoDetail> findMovieDetail(Long id);

    Movie findMovieById(Long id);


}
