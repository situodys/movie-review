package com.example.mreview.domain.review;

import com.example.mreview.domain.member.Member;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.review.dto.ReviewDTO;
import com.example.mreview.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"movie","member"})
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private int grade;

    private String text;

    public void changeGrade(int grade) {
        this.grade = grade;
    }

    public void changeText(String text) {
        this.text = text;
    }

    public ReviewDTO toDTO() {
        return ReviewDTO.builder()
                .reviewNo(this.reviewnum)
                .memberId(this.member.getMid())
                .movieNo(this.movie.getMno())
                .nickname(this.member.getNickname())
                .grade(this.grade)
                .text(this.text)
                .modDate(this.getModDate())
                .regDate(this.getRegDate())
                .build();
    }

}
