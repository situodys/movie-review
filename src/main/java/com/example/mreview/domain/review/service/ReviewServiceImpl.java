package com.example.mreview.domain.review.service;

import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.review.Review;
import com.example.mreview.domain.review.dto.ReviewDTO;
import com.example.mreview.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getReviewsOfMovie(Long mno) {
        Movie targetMovie = Movie.builder().mno(mno).build();
        List<Review> reviews = reviewRepository.getReviewsByMovie(targetMovie);
        return reviews.stream()
                .map(Review::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Review save = reviewRepository.save(reviewDTO.toEntity());
        return save.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {

        Optional<Review> result = reviewRepository.findById(reviewDTO.getReviewNo());

        if (result.isPresent()) {
            Review oldReview = result.get();
            oldReview.changeGrade(reviewDTO.getGrade());
            oldReview.changeText(reviewDTO.getText());

            reviewRepository.save(oldReview);
        }
    }

    @Override
    public void remove(Long reviewnum) {
        reviewRepository.deleteById(reviewnum);
    }
}
