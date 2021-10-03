package com.robsonmrsp.netflics.repository;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.robsonmrsp.netflics.entity.Movie;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
@Slf4j
public class MovieRepository {

  private final DatabaseClient databaseClient;

  public static final BiFunction<Row, RowMetadata, Movie> MAPPING_FUNCTION =
      (row, rowMetaData) ->
          Movie.builder()
              .id(row.get("id", UUID.class))
              .title(row.get("title", String.class))
              .build();

  //
  //    public Flux<Movie> findByTitleContains(String name) {
  //        return this.databaseClient
  //                .sql("SELECT * FROM Filmes WHERE title LIKE :title")
  //                .bind("title", "%" + name + "%")
  //                .map(MAPPING_FUNCTION)
  //                .all();
  //    }
  //
  //    public Flux<Movie> findAll() {
  //        return this.databaseClient
  //                .sql("SELECT * FROM Filmes")
  //                .filter((statement, executeFunction) -> statement.fetchSize(10).execute())
  //                .map(MAPPING_FUNCTION)
  //                .all();
  //    }
  //
  //    // see: https://stackoverflow.com/questions/64267699/spring-data-r2dbc-and-group-by
  //    public Flux<Map<Object, Object>> countByStatus() {
  //        return this.databaseClient
  //                .sql("SELECT count(*) as cnt, status FROM Filmes group by status")
  //                .map((row, rowMetadata) -> {
  //                    Long cnt = row.get("cnt", Long.class);
  ////                    Movie.Status s = row.get("status", Movie.Status.class);
  //
  //                    return Map.<Object, Object>of("cnt", cnt, "status", s);
  //                })
  //                .all();
  //    }

  public Mono<Movie> findById(UUID id) {
    return this.databaseClient
        .sql("SELECT * FROM Movie WHERE id=:id")
        .bind("id", id)
        .map(MAPPING_FUNCTION)
        .one();
  }

  public Flux<Movie> findAll() {
    return this.databaseClient.sql("SELECT * FROM Movie ").map(MAPPING_FUNCTION).all();
  }

  // TODO change this to return a Movie
  public Mono<UUID> save(Movie p) {
    return this.databaseClient
        .sql("INSERT INTO  Movie (title) VALUES (:title)")
        .filter((statement, executeFunction) -> statement.returnGeneratedValues("id").execute())
        .bind("title", p.getTitle())
        .fetch()
        .first()
        .map(r -> (UUID) r.get("id"));
  }

  // see: https://github.com/spring-projects/spring-data-r2dbc/issues/259
  // and
  // https://stackoverflow.com/questions/62514094/how-to-execute-multiple-inserts-in-batch-in-r2dbc
  public Flux<UUID> saveAll(List<Movie> data) {
    return this.databaseClient.inConnectionMany(
        connection -> {
          var statement =
              connection
                  .createStatement("INSERT INTO  Movie (title) VALUES ($1)")
                  .returnGeneratedValues("id");

          for (var p : data) {
            statement.bind(0, p.getTitle()).add();
          }

          return Flux.from(statement.execute())
              .flatMap(result -> result.map((row, rowMetadata) -> row.get("id", UUID.class)));
        });
  }

  public Mono<Integer> update(Movie p) {
    return this.databaseClient
        .sql("UPDATE Movie set title=:title WHERE id=:id")
        .bind("title", p.getTitle())
        .bind("id", p.getId())
        .fetch()
        .rowsUpdated();
  }

  public Mono<Integer> deleteById(UUID id) {
    return this.databaseClient
        .sql("DELETE FROM Movie WHERE id=:id")
        .bind("id", id)
        .fetch()
        .rowsUpdated();
  }

  public Mono<Integer> deleteAll() {
    return this.databaseClient.sql("DELETE FROM Movie").fetch().rowsUpdated();
  }
}
