package com.robsonmrsp.netflics.service;

import java.util.Optional;
import java.util.UUID;

import com.robsonmrsp.netflics.commonlibs.FilterMovie;
import com.robsonmrsp.netflics.commonlibs.Pager;
import com.robsonmrsp.netflics.commonlibs.SearchParameters;
import com.robsonmrsp.netflics.entity.Movie;

public interface MovieService {

  public Optional<Movie> get(String id);

  public Optional<Movie> get(UUID id);

  public Pager<Movie> get(SearchParameters<FilterMovie> searchParams);

  public void delete(UUID id);

  public void delete(String id);

  public Movie save(Movie entity);

  public Movie update(Movie entity);
}
