package com.example.mreview.domain.review.repository;

import com.example.mreview.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
