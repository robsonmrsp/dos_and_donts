package com.robsonmrsp.netflics.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.robsonmrsp.netflics.commonlibs.FilterMovie;
import com.robsonmrsp.netflics.commonlibs.Pager;
import com.robsonmrsp.netflics.commonlibs.SearchParameters;
import com.robsonmrsp.netflics.entity.Movie;
import com.robsonmrsp.netflics.repository.MovieRepository;
import com.robsonmrsp.netflics.repository.MovieSpecificationHelper;

@Service
public class MovieServiceImp implements MovieService {

  @Autowired MovieRepository repository;

  @Override
  public Optional<Movie> get(UUID id) {
    return repository.findById(id);
  }

  @Override
  public Optional<Movie> get(String id) {
    return repository.findById(UUID.fromString(id));
  }

  @Override
  public Pager<Movie> get(SearchParameters<FilterMovie> searchParams) {
    Pageable pageRequest = searchParams.getPageRequest();

    Page<Movie> page =
        repository.findAll(MovieSpecificationHelper.filter(searchParams), pageRequest);

    return new Pager<Movie>(
        page.getContent(),
        searchParams.getPage(),
        searchParams.getPageSize(),
        searchParams.getOrder(),
        searchParams.getOrderBy(),
        page.getTotalElements());
  }

  @Override
  public Movie save(Movie entity) {
    return repository.save(entity);
  }

  @Override
  public Movie update(Movie entity) {

    return repository.save(entity);
  }

  @Override
  public void delete(String id) {
    delete(UUID.fromString(id));
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }
}
