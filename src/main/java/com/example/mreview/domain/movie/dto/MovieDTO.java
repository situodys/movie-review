package com.example.mreview.domain.movie.dto;

import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movieimage.MovieImage;
import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mysema.commons.lang.Assert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime regDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime modDate;


    @Builder
    public MovieDTO(Long mno, String title, List<MovieImageDTO> movieImageDTOList,LocalDateTime regDate,LocalDateTime modDate) {
//        Assert.notNull(mno, "mno must not be null");
        Assert.hasText(title, "title must not be null");

        this.mno = mno;
        this.title = title;
        if (movieImageDTOList != null) {
            this.movieImageDTOList = movieImageDTOList;
        }
        this.regDate = regDate;
        this.modDate = modDate;
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

    public void initMovieImageList(List<MovieImageDTO> movieImageDTOList) {
        this.movieImageDTOList = movieImageDTOList;
    }

}
