package com.example.mreview.domain.movie.repository;

import static com.example.mreview.entity.QMovie.movie;

import static com.example.mreview.entity.QMovieImage.movieImage;
import static com.example.mreview.entity.QReview.review;

import com.example.mreview.domain.movie.Movie;
import com.example.mreview.domain.movie.dto.MovieInfoDetailDTO;
import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import com.example.mreview.global.dto.PageRequestDTO;
import com.example.mreview.global.dto.PageResponseDTO;
import com.mysql.cj.util.StringUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieCustomRepositoryImpl extends QuerydslRepositorySupport implements MovieCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public MovieCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Movie.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<MovieListInfoDTO> getListPage(PageRequestDTO pageRequestDTO) {

        PageRequest pageRequest = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize(), Sort.by("mno").descending());

        JPAQuery<MovieListInfoDTO> query = jpaQueryFactory.select(Projections.constructor(
                        MovieListInfoDTO.class,
                        movie,
                        movieImage,
                        review.grade.avg().coalesce(0.0),
                        review.countDistinct()
                ))
                .from(movie)
                .leftJoin(movieImage).on(movieImage.movie.eq(movie))
                .leftJoin(review).on(review.movie.eq(movie))
                .where(
                        isTitleEq(pageRequestDTO.getTitle())
                )
                .groupBy(movie);

        Long totalCount = pageRequestDTO.getTotalCount() == null ? query.fetch().size() : pageRequestDTO.getTotalCount();

        List<MovieListInfoDTO> result = this.getQuerydsl().applyPagination(pageRequest, query).fetch();
        return new PageImpl<>(result, pageRequest, totalCount);
    }

//    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
//        List<OrderSpecifier> orders = new ArrayList<>();
//
//        sort.stream().forEach(order->{
//            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
//            String prop = order.getProperty();
//            PathBuilder orderExpression = new PathBuilder(Movie.class, "movie");
//            orders.add(new OrderSpecifier(direction, orderExpression.get(prop)));
//        });
//        return orders;
//    }

    private BooleanExpression isTitleEq(String keyword) {
        if (StringUtils.isNullOrEmpty(keyword)) return null;
        return movie.title.containsIgnoreCase(keyword);
    }

    @Override
    public Long getTotalCount() {
        Long fetchCount = jpaQueryFactory.select(movie.countDistinct())
                .from(movie)
                .fetchOne();
        return fetchCount;
    }

    @Override
    public List<MovieInfoDetailDTO> findMovieDetail(Long id) {

        List<MovieInfoDetailDTO> results = jpaQueryFactory.select(
                        Projections.constructor(
                                MovieInfoDetailDTO.class,
                                movie,
                                movieImage,
                                review.grade.avg().coalesce(0.0),
                                review.countDistinct()
                        ))
                .from(movie)
                .leftJoin(review).on(review.movie.eq(movie))
                .leftJoin(movieImage).on(movieImage.movie.eq(movie))
                .where(movie.mno.eq(id))
                .groupBy(movieImage)
                .fetch();
        return results;
    }
}
