package com.example.mreview.domain.movie.dto;

import com.example.mreview.domain.movie.Movie;
import com.example.mreview.domain.movieimage.MovieImage;
import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
import com.mysema.commons.lang.Assert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@ToString(exclude = "movieImageDTOList")
public class MovieDTO implements Serializable {

    private Long mno;
    private String title;

    private List<MovieImageDTO> movieImageDTOList = new ArrayList<>();

    @Builder
    public MovieDTO(Long mno, String title, List<MovieImageDTO> movieImageDTOList) {
//        Assert.notNull(mno, "mno must not be null");
        Assert.hasText(title, "title must not be null");

        this.mno = mno;
        this.title = title;
        if (movieImageDTOList != null) {
            this.movieImageDTOList = movieImageDTOList;
        }
    }

    public Movie toEntity() {
        Movie movie = Movie.builder()
                .mno(this.mno)
                .title(this.title)
                .build();

        if (!CollectionUtils.isEmpty(movieImageDTOList)) {
            List<MovieImage> movieImageList = movieImageDTOList.stream()
                    .map(movieImageDTO -> movieImageDTO.toEntity(movie))
                    .collect(Collectors.toList());

            movie.initMovieImageLists(movieImageList);
        }
        return movie;
    }

}
