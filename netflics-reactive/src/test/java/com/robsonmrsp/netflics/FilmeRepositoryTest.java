package com.robsonmrsp.netflics;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.robsonmrsp.netflics.entity.Movie;
import com.robsonmrsp.netflics.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.test.StepVerifier;

@Slf4j
@SpringJUnitConfig(classes = {ReactiveTestConfiguration.class, FilmeRepositoryTest.TestConfig.class})
@Import(ReactiveTestConfiguration.class)
@ActiveProfiles({"test"})
public class FilmeRepositoryTest {

  @Autowired MovieRepository filmes;

  @BeforeEach
  public void setup() {
    this.filmes.deleteAll().subscribe(data -> log.info("clean database: {} deleted.", data));
  }

  @Test
  public void testSaveAll() {

    var data = Movie.builder().title("test").build();
    var data1 = Movie.builder().title("test1").build();

    var result =
        filmes
            .saveAll(List.of(data, data1))
            .log("[Generated result]")
            .doOnNext(id -> log.info("generated id: {}", id));

    assertThat(result).isNotNull();
    result.as(StepVerifier::create).expectNextCount(2).verifyComplete();

    StepVerifier.create(filmes.findAll())
        .assertNext(
            f -> {
              log.info("data: {}", f);
              assertThat(f.getTitle()).isEqualTo("test");
            })
        .assertNext(
            f -> {
              log.info("data: {}", f);
              assertThat(f.getTitle()).isEqualTo("test1");
            })
        .verifyComplete();
  }

  // see:
  // https://stackoverflow.com/questions/64374730/java-r2dbc-client-execute-sql-and-use-returned-id-for-next-execute/64409363#64409363
  @Test
  public void testInsertAndQuery() {
    var data = Movie.builder().title("test").build();
    this.filmes
        .save(data)
        .flatMap(id -> this.filmes.findById(id))
        .as(StepVerifier::create)
        .consumeNextWith(
            r -> {
              log.info("result data: {}", r);
              assertThat(r.getTitle()).isEqualTo("test");
            })
        .verifyComplete();
  }

  @ComponentScan
  static class TestConfig {}
}
