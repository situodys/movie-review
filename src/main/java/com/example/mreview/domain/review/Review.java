package com.example.mreview.domain.review;

import com.example.mreview.domain.member.Member;
import com.example.mreview.domain.movie.Movie;
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
}
