package com.example.mreview.domain.review.repository;

import com.example.mreview.domain.member.Member;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.review.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository  extends JpaRepository<Review,Long>,ReviewCustomRepository {
    void deleteByMember(Member member);
}
