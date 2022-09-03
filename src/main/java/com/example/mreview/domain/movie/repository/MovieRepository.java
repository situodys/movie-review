package com.example.mreview.domain.movie.repository;

import com.example.mreview.domain.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>,MovieCustomRepository {
//    List<Object[]> getMovieWithAll(Long mno);
//    @Query("select m, avg(coalesce(r.grade,0)),count(distinct r)" +
//            " from Movie m" +
//            " left outer join Review r" +
//            " on r.movie = m group by m")


}
