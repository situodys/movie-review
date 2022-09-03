package com.example.mreview.domain.review.repository;

import com.example.mreview.domain.member.Member;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.review.Review;

import java.util.List;

public interface ReviewCustomRepository {
    List<Review> getReviewsByMovie(Movie movie);

    void deleteByReviewNum(Long reviewnum);
}
