package com.example.mreview.domain.movie.service;

import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movie.dto.MovieDTO;
import com.example.mreview.domain.movie.entity.MovieListInfo;
import com.example.mreview.domain.movie.repository.MovieRepository;
import com.example.mreview.domain.movieimage.repository.MovieImageRepository;
import com.example.mreview.global.dto.PageRequestDTO;
import com.example.mreview.global.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Override
    @Transactional
    public Long register(MovieDTO movieDTO) {
        Movie movie = movieDTO.toEntity();

        movieRepository.save(movie);
        movie.getMovieImageLists()
                .forEach(movieImage ->{
                    movieImageRepository.save(movieImage);
                });
        return movie.getMno();
    }

    @Override
    public PageResponseDTO getList(PageRequestDTO pageRequestDTO) {

        Page<MovieListInfoDTO> result = movieRepository.getListPage(pageRequestDTO);
        PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                .result(result)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }
}
