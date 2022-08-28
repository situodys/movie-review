package com.example.mreview.domain.movieimage;

import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
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

    public MovieImageDTO toDTO() {
        MovieImageDTO movieImageDTO = MovieImageDTO.builder()
                .fileName(this.imgName)
                .folderPath(this.imgPath)
                .uuid(this.uuid)
                .build();

        movieImageDTO.changeMno(this.movie.getMno());
        return movieImageDTO;
    }
}
