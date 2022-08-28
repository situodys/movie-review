package com.example.mreview.domain.movieimage.dto;

import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movieimage.MovieImage;
import com.mysema.commons.lang.Assert;
import lombok.*;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@NoArgsConstructor
@Getter
@ToString
public class MovieImageDTO implements Serializable {
    private static final String THUMBNAIL_IDENTIFIER = "s_";

    private String fileName;
    private String uuid;
    private String folderPath;

    private Long mno;

    @Builder
    public MovieImageDTO(String fileName, String uuid, String folderPath) {
        Assert.hasText(fileName, "fileName must not be empty");
        Assert.hasText(uuid, "uuid must not be empty");
        Assert.hasText(folderPath, "folderPath must not be empty");

        this.fileName =fileName;
        this.uuid=uuid;
        this.folderPath =folderPath;
    }

    public String getImageURL() {
        try {
            return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(folderPath + "/"+THUMBNAIL_IDENTIFIER + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public MovieImage toEntity(Movie movie){
        return MovieImage.builder()
                .movie(movie)
                .imgName(this.fileName)
                .imgPath(this.folderPath)
                .uuid(this.uuid)
                .build();
    }

    public void changeMno(Long mno) {
        this.mno=mno;
    }
}
