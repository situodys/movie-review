package com.example.mreview.domain.movie.entity;

import com.example.mreview.domain.movie.dto.MovieDTO;
import com.example.mreview.domain.movieimage.MovieImage;
import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
import com.example.mreview.global.entity.BaseEntity;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = "movieImageLists")
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;

    @OneToMany(mappedBy ="movie" )
    List<MovieImage> movieImageLists = new ArrayList<>();

    @Builder
    public Movie(Long mno, String title, List<MovieImage> movieImageLists) {
        this.mno =mno;
        this.title = title;
        this.movieImageLists = movieImageLists;
    }

    public void initMovieImageLists(List<MovieImage> movieImageLists) {
        this.movieImageLists = movieImageLists;
    }

    public MovieDTO toDTO() {
        MovieDTO movieDTO = MovieDTO.builder()
                .mno(this.mno)
                .title(this.title)
                .regDate(this.getRegDate())
                .modDate(this.getModDate())
                .build();

        if (!CollectionUtils.isEmpty(this.movieImageLists)) {
            List<MovieImageDTO> movieImageDTOList = movieImageLists.stream()
                    .map(MovieImage::toDTO)
                    .collect(Collectors.toList());

            movieDTO.initMovieImageList(movieImageDTOList);
        }
        return movieDTO;
    }

    public MovieDTO toMemberOfListInfoDTO() {
        return MovieDTO.builder()
                .mno(this.mno)
                .title(this.title)
                .regDate(this.getRegDate())
                .modDate(this.getModDate())
                .build();
    }
}

