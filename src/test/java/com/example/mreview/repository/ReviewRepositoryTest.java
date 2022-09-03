package com.example.mreview.repository;

import com.example.mreview.domain.member.Member;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.review.Review;
import com.example.mreview.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertDummyReview() throws Exception{
        //give
        IntStream.rangeClosed(1,200).forEach(i->{
            long movieNo = (long) (Math.random() * 100) + 1;
            Movie movie = Movie.builder()
                    .mno(movieNo)
                    .build();

            long memberId = (long)(Math.random()*100)+1;
            Member member = Member.builder()
                    .mid(memberId)
                    .build();

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade((int) (Math.random() * 5) + 1)
                    .text("영화에 대한 느낌..." + i)
                    .build();

            reviewRepository.save(review);

        });
        //when

        //then
    }

    @Test
    public void findByMovieTest() throws Exception{
        //give
        Movie movie = Movie.builder()
                .mno(100L)
                .build();
        //when
        List<Review> reviews = reviewRepository.getReviewsByMovie(movie);
        //then
        for (Review review : reviews) {
            System.out.println(review);
            System.out.println(review.getMember());
        }
    }
}