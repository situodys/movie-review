package com.example.mreview.domain.movie.entity;

import com.example.mreview.domain.movie.dto.MovieDTO;
import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import com.example.mreview.domain.movieimage.MovieImage;
import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
import lombok.*;
import org.springframework.util.Assert;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "movieImageList")
public class MovieListInfo {
    private Movie movie;
    private MovieImage movieImage;
    private double starRating;
    private long reviewCount;

    @Builder
    public MovieListInfo(Movie movie, MovieImage movieImage, double starRating, long reviewCount) {
        Assert.notNull(movie, "movie must not be null");

        this.movie =movie;
        this.movieImage = movieImage;
        this.starRating = starRating;
        this.reviewCount = reviewCount;
    }

    public MovieListInfoDTO toDTO() {
        return MovieListInfoDTO.builder()
                .movieDTO(movie.toMemberOfListInfoDTO())
                .movieImageDTO(movieImage.toDTO())
                .starRating(this.starRating)
                .reviewCount(this.reviewCount)
                .build();
    }
}
