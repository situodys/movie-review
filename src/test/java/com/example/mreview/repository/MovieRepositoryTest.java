package com.example.mreview.repository;


import com.example.mreview.domain.movie.dto.MovieInfoDetailDTO;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movie.entity.MovieInfoDetail;
import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import com.example.mreview.domain.movieimage.MovieImage;

import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
import com.example.mreview.domain.movieimage.repository.MovieImageRepository;
import com.example.mreview.domain.movie.repository.MovieRepository;
import com.example.mreview.global.dto.PageRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;


@SpringBootTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    @Transactional
    @Commit
    public void insertDummyMovies() throws Exception {
        //give
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("Movie..." + i)
                    .build();

            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .imgPath("test" + j + ".jpg")
                        .build();
                movieImageRepository.save(movieImage);
            }
        });
        //when

        //then
    }

    @Test
    @DisplayName("영화와 리뷰를 통한 페이징 처리된 목록")
    public void listPageTest() throws Exception {
        PageRequestDTO rq = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .totalCount(-1L)
                .build();

        Page<MovieListInfoDTO> lists = movieRepository.getListPage(rq);

        System.out.println(lists.getTotalElements());
        for (MovieListInfoDTO list : lists.getContent()) {
            System.out.println(list);
        }


    }

    @Test
    public void findMovieDetailTest() throws Exception {
        //give
        Long id = 108L;
        //when
        List<MovieInfoDetail> results = movieRepository.findMovieDetail(id);

        //then
        for (MovieInfoDetail result : results) {
            System.out.println("result = " + result);
        }

    }




//    @Test
//    public void findMovieByIdTest() throws Exception {
//        //give
//        Long id = 99L;
//        //when
//        Movie movie = movieRepository.findMovieById(id);
//        //then
//        System.out.println(movie);
//        for (MovieImage movieImageList : movie.getMovieImageLists()) {
//            System.out.println(movieImageList);
//        }
//
//    }
}