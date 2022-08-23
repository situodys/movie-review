package com.example.mreview.domain.movie;

import com.example.mreview.domain.movieimage.MovieImage;
import com.example.mreview.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
}

