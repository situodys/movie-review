package com.example.mreview.domain.review.repository;

import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.review.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.mreview.domain.movie.entity.QMovie.movie;
import static com.example.mreview.domain.member.QMember.member;
import static com.example.mreview.domain.review.QReview.*;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewCustomRepository  {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Review> getReviewsByMovie(Movie targetMovie) {
        List<Review> list = jpaQueryFactory.select(review)
                .from(review)
                .leftJoin(review.movie,movie).fetchJoin()
                .leftJoin(review.member, member).fetchJoin()
                .where(review.movie.eq(targetMovie))
                .fetch();

        return list;
    }

    @Override
    public void deleteByReviewNum(Long reviewnum) {
        long deleteId = jpaQueryFactory.delete(review)
                .where(review.reviewnum.eq(reviewnum))
                .execute();
    }
}
