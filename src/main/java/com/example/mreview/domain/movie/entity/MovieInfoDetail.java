package com.example.mreview.domain.movie.entity;

import com.example.mreview.domain.movie.dto.MovieInfoDetailDTO;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movieimage.MovieImage;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString(exclude = "movieImages")
public class MovieInfoDetail {

    private Movie movie;
    private MovieImage movieImage;
    private Double rating;
    private Long reviewCount;

    @Builder
    public MovieInfoDetail(Movie movie, MovieImage movieImage, Double rating, Long reviewCount) {
        this.movie=movie;
        this.movieImage = movieImage;
        this.rating=rating;
        this.reviewCount = reviewCount;
    }

    public MovieInfoDetailDTO toDTO() {
        MovieInfoDetailDTO movieInfoDetailDTO = MovieInfoDetailDTO.builder()
                .movieDTO(movie.toDTO())
                .rating(this.rating)
                .reviewCount(this.reviewCount)
                .build();

        return movieInfoDetailDTO;
    }

    public void setMovie(Movie movie) {
        this.movie=movie;
    }

}
