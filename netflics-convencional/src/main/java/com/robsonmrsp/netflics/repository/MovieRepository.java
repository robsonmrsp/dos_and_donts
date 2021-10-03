package com.robsonmrsp.netflics.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.robsonmrsp.netflics.entity.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, UUID>, JpaSpecificationExecutor<Movie>{}
