package com.example.mreview.domain.movieimage.repository;

import com.example.mreview.domain.movieimage.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage,Long> {
}
