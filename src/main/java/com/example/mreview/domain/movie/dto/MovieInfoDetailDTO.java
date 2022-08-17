package com.example.mreview.domain.movie.dto;

import com.example.mreview.domain.movie.Movie;
import com.example.mreview.domain.movieimage.MovieImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfoDetailDTO {

    private Movie movie;
    private MovieImage movieImage;
    private double rating;
    private Long reviewCount;

}
