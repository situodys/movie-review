package com.example.mreview.domain.movie.repository;



import com.example.mreview.domain.movie.dto.MovieInfoDetailDTO;
import com.example.mreview.domain.movie.entity.Movie;
import com.example.mreview.domain.movie.entity.MovieInfoDetail;
import com.example.mreview.domain.movie.dto.MovieListInfoDTO;
import com.example.mreview.domain.movie.entity.MovieListInfo;
import com.example.mreview.domain.movieimage.MovieImage;
import com.example.mreview.domain.movieimage.dto.MovieImageDTO;
import com.example.mreview.global.dto.PageRequestDTO;
import com.mysql.cj.util.StringUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.mreview.domain.movie.QMovie.movie;
import static com.example.mreview.domain.movieimage.QMovieImage.movieImage;
import static com.example.mreview.domain.review.QReview.review;

@Repository
public class MovieCustomRepositoryImpl extends QuerydslRepositorySupport implements MovieCustomRepository {

    private static final Long NEED_CALCULATE = -1L;
    private final JPAQueryFactory jpaQueryFactory;

    public MovieCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Movie.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<MovieListInfoDTO> getListPage(PageRequestDTO pageRequestDTO) {

        PageRequest pageRequest = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize(), Sort.by("mno").descending());

        JPAQuery<MovieListInfo> query = jpaQueryFactory.select(Projections.constructor(
                        MovieListInfo.class,
                        movie,
                        movieImage,
                        review.grade.avg().coalesce(0.0),
                        review.countDistinct()
                ))
                .from(movie)
                .leftJoin(movieImage).on(movieImage.movie.eq(movie))
                .leftJoin(review).on(review.movie.eq(movie))
                .where(
                        isTitleContains(pageRequestDTO.getTitle())
                )
                .groupBy(movie);

        Long totalCount = pageRequestDTO.getTotalCount()==NEED_CALCULATE? query.fetch().size() : pageRequestDTO.getTotalCount();

        List<MovieListInfo> result = this.getQuerydsl().applyPagination(pageRequest, query).fetch();

        List<MovieListInfoDTO> resultToDTO = result.stream()
                .map(MovieListInfo::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(resultToDTO, pageRequest, totalCount);
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

    private BooleanExpression isTitleContains(String keyword) {
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
    public List<MovieInfoDetail> findMovieDetail(Long id) {

        List<MovieInfoDetail> results = jpaQueryFactory.select(
                        Projections.constructor(
                                MovieInfoDetail.class,
                                movie,
                                movieImage,
                                review.grade.avg().coalesce(0.0),
                                review.countDistinct()
                        ))
                .from(movie)
                .leftJoin(movie.movieImageLists,movieImage)
                .leftJoin(review).on(review.movie.eq(movie))
                .where(movie.mno.eq(id))
                .groupBy(movieImage)
                .fetch();

        return results;
    }

    public Movie findMovieById(Long id) {
        return jpaQueryFactory.select(movie)
                .from(movie)
                .leftJoin(movie.movieImageLists,movieImage)
                .fetchJoin()
                .where(movie.mno.eq(id))
                .fetchOne();
    }
}
