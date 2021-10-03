package com.robsonmrsp.netflics.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.robsonmrsp.netflics.commonlibs.FilterMovie;
import com.robsonmrsp.netflics.commonlibs.SearchParameters;
import com.robsonmrsp.netflics.commonlibs.Util;
import com.robsonmrsp.netflics.entity.Movie;

@SuppressWarnings("serial")
public class MovieSpecificationHelper {

  public static Specification<Movie> filter(SearchParameters<FilterMovie> searchParam) {
    return new Specification<Movie>() {

      @Override
      public Predicate toPredicate(
          Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        FilterMovie filterMovie = searchParam.getFilter();
        if (filterMovie.getTitle() != null) {
          predicates.add(
              criteriaBuilder.like(
                  criteriaBuilder.upper(root.<String>get("title")),
                  Util.wrapSufix(filterMovie.getTitle().toUpperCase(), searchParam.isExact())));
        }
        if (filterMovie.getReleaseDate() != null) {
          predicates.add(
              criteriaBuilder.equal(root.get("releaseDate"), filterMovie.getReleaseDate()));
        }
        if (filterMovie.getGenre() != null) {
          predicates.add(
              criteriaBuilder.equal(root.get("genre").get("id"), filterMovie.getGenre()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
      }
    };
  }
}
