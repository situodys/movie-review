package com.example.mreview.domain.movie.service;

import com.example.mreview.domain.movie.dto.MovieDTO;
import com.example.mreview.domain.movie.dto.MovieInfoDetailDTO;
import com.example.mreview.global.dto.PageRequestDTO;
import com.example.mreview.global.dto.PageResponseDTO;

public interface MovieService {
    Long register(MovieDTO movieDTO);

    PageResponseDTO getList(PageRequestDTO pageRequestDTO);

    MovieInfoDetailDTO getMovie(Long mno);
}
