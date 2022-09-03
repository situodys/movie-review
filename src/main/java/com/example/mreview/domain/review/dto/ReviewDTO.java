package com.example.mreview.domain.review.dto;

import com.example.mreview.domain.member.Member;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    private Long reviewNo;

    //movie
    private Long movieNo;

    //memberId
    private Long memberId;

    private String nickname;
    private String email;

    private int grade;

    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime regDate, modDate;

    public Review toEntity() {
        return Review.builder()
                .reviewnum(this.reviewNo)
                .member(Member.builder().mid(memberId).build())
                .movie(Movie.builder().mno(movieNo).build())
                .grade(this.grade)
                .text(this.text)
                .build();
    }

}
