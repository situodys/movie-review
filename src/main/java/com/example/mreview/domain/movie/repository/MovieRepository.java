package com.example.mreview.domain.movie.repository;

import com.example.mreview.domain.movie.Movie;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>,MovieCustomRepository {

//    @Query("select m, avg(coalesce(r.grade,0)),count(distinct r)" +
//            " from Movie m" +
//            " left outer join Review r" +
//            " on r.movie = m group by m")


}
