package com.example.mreview.domain.movie.dto;

import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
import lombok.*;
import org.springframework.util.Assert;


@Getter
@NoArgsConstructor
@ToString(exclude = "movieImageList")
public class MovieListInfoDTO {
    private MovieDTO movieDTO;
    private MovieImageDTO movieImageDTO;
    private double starRating;
    private long reviewCount;

    @Builder
    public MovieListInfoDTO(MovieDTO movieDTO, MovieImageDTO movieImageDTO, double starRating, long reviewCount) {
        Assert.notNull(movieDTO, "movie must not be null");

        this.movieDTO=movieDTO;
        this.movieImageDTO = movieImageDTO;
        this.starRating = starRating;
        this.reviewCount = reviewCount;
    }
}
