package com.example.mreview.domain.review.service;

import com.example.mreview.domain.review.Review;
import com.example.mreview.domain.review.dto.ReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewService  {

    List<ReviewDTO> getReviewsOfMovie(Long mno);

    Long register(ReviewDTO reviewDTO);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewnum);


}
