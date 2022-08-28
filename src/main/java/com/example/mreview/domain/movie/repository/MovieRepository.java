package com.example.mreview.domain.movie.repository;

import com.example.mreview.domain.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>,MovieCustomRepository {

//    @Query("select m, avg(coalesce(r.grade,0)),count(distinct r)" +
//            " from Movie m" +
//            " left outer join Review r" +
//            " on r.movie = m group by m")


}
