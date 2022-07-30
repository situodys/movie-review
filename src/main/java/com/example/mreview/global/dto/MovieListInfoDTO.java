package com.example.mreview.global.dto;

import com.example.mreview.domain.movie.Movie;
import com.example.mreview.domain.movieimage.MovieImage;
import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieListInfoDTO {
    private Movie movie;
    private MovieImage movieImage;
    private double starRating;
    private int reviewCount;

    public MovieListInfoDTO(List<Tuple> tuples) {
        this.movie = (Movie) tuples.get(0);

    }
}
