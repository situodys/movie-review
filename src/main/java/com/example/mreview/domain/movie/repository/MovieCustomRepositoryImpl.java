package com.example.mreview.domain.movie.repository;

import static com.example.mreview.entity.QMovie.movie;

import static com.example.mreview.entity.QReview.review;

import com.example.mreview.domain.movie.Movie;
import com.example.mreview.global.dto.MovieListInfoDTO;
import com.example.mreview.global.dto.PageRequestDTO;
import com.example.mreview.global.dto.PageResponseDTO;
import com.example.mreview.global.entity.SearchTypes;
import com.example.mreview.global.entity.SearchValue;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public PageResponseDTO getListPage(PageRequestDTO pageRequestDTO) {

        Long totalCount=0L;
        String type = pageRequestDTO.getSearchType();
        String keyword = pageRequestDTO.getKeyword();

        if (pageRequestDTO.getTotalCount().isEmpty()) {
            totalCount = getTotalCount();
        }
        List<Tuple> result = jpaQueryFactory.select(movie, review.grade.avg())
                .from(movie)
                .leftJoin(review).on(review.movie.eq(movie))
                .where(
                        movie.mno.gt(pageRequestDTO.getLastMno()),
                        getEqTitle(type, keyword)
                )
                .orderBy(getOrderSpecifier(pageRequestDTO.getSort())
                        .stream().toArray(OrderSpecifier[]::new))
                .limit(pageRequestDTO.getSize())
                .groupBy(movie)
                .fetch()
                .stream().map();

        new PageResponseDTO(result, )

        return null;
    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        sort.stream().forEach(order->{
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderExpression = new PathBuilder(Movie.class, "movie");
            orders.add(new OrderSpecifier(direction, orderExpression.get(prop)));
        });
        return orders;
    }

//    private BooleanBuilder makeConditions(PageRequestDTO pageRequestDTO) {
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//
//        booleanBuilder.and(movie.mno.gt(pageRequestDTO.getLastMno()));
//
//        String keyword = pageRequestDTO.getKeyword();
//        if(keyword==null) return booleanBuilder;
//        List<SearchValue> searchTypes = pageRequestDTO.getSearchTypes();
//    }

    private BooleanExpression getEqTitle(String type, String keyword) {
        if (type == null || !type.contains("t")) {
            return null;
        }
        return movie.title.containsIgnoreCase(keyword);
    }

    @Override
    public Long getTotalCount() {
        List<Long> result = jpaQueryFactory.select(movie.countDistinct())
                .from(movie)
                .fetch();
        return result.get(0);
    }
}
