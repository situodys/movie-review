package com.example.mreview.domain.movieimage;

import com.example.mreview.domain.movie.Movie;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;

    private String imgName;

    private String imgPath;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name= "movie_mno")
    private Movie movie;

    @Builder
    public MovieImage(String uuid, String imgName, String imgPath, Movie movie) {
        this.uuid = uuid;
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.movie = movie;
    }
}
