package com.example.mreview.domain.movie.dto;

import com.example.mreview.domain.movie.entity.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(exclude = "movieImages")
public class MovieInfoDetailDTO {
    private MovieDTO movieDTO;
    private Double rating;
    private Long reviewCount;

    @Builder
    public MovieInfoDetailDTO(MovieDTO movieDTO, Double rating, Long reviewCount) {
        this.movieDTO=movieDTO;
        this.rating=rating;
        this.reviewCount = reviewCount;
    }

}
