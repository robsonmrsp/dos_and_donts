package com.robsonmrsp.netflics.commonlibs;

import java.util.ArrayList;
import java.util.List;

import com.robsonmrsp.netflics.entity.Actor;
import com.robsonmrsp.netflics.entity.Genre;
import com.robsonmrsp.netflics.entity.Movie;

public class Parser {
  private static JsonGenre toBasicJson(Genre genre) {
    JsonGenre jsonGenre = new JsonGenre();
    applyBasicJsonValues(jsonGenre, genre);
    return jsonGenre;
  }

  private static Genre toBasicEntity(JsonGenre jsonGenre) {
    Genre genre = new Genre();
    applyBasicEntityValues(genre, jsonGenre);
    return genre;
  }

  private static void applyBasicJsonValues(JsonGenre jsonGenre, Genre genre) {
    jsonGenre.setId(genre.getId());
    jsonGenre.setName(genre.getName());
  }

  private static void applyBasicEntityValues(Genre genre, JsonGenre jsonGenre) {
    genre.setId(jsonGenre.getId());
    genre.setName(jsonGenre.getName());
  }

  public static JsonGenre toJson(Genre genre) {
    if (genre == null) {
      return null;
    }

    JsonGenre jsonGenre = new JsonGenre();

    applyBasicJsonValues(jsonGenre, genre);

    return jsonGenre;
  }

  public static Genre apply(Genre genre, JsonGenre jsonGenre) {

    if (genre == null) genre = new Genre();

    applyBasicEntityValues(genre, jsonGenre);

    return genre;
  }

  public static Genre toEntity(JsonGenre jsonGenre) {
    Genre genre = new Genre();

    return apply(genre, jsonGenre);
  }

  public static List<JsonGenre> toListJsonGenres(List<Genre> all) {
    List<JsonGenre> jsonGenres = new ArrayList<JsonGenre>();
    for (Genre genre : all) {
      jsonGenres.add(toJson(genre));
    }
    return jsonGenres;
  }

  // converte de entidade para json --------------------
  private static JsonMovie toBasicJson(Movie movie) {
    JsonMovie jsonMovie = new JsonMovie();
    applyBasicJsonValues(jsonMovie, movie);
    return jsonMovie;
  }

  private static Movie toBasicEntity(JsonMovie jsonMovie) {
    Movie movie = new Movie();
    applyBasicEntityValues(movie, jsonMovie);
    return movie;
  }

  private static void applyBasicJsonValues(JsonMovie jsonMovie, Movie movie) {
    jsonMovie.setId(movie.getId());
    jsonMovie.setTitle(movie.getTitle());
    jsonMovie.setBudget(movie.getBudget());
    jsonMovie.setReleaseDate(movie.getReleaseDate());
  }

  private static void applyBasicEntityValues(Movie movie, JsonMovie jsonMovie) {
    movie.setId(jsonMovie.getId());
    movie.setTitle(jsonMovie.getTitle());
    movie.setBudget(jsonMovie.getBudget());
    movie.setReleaseDate(jsonMovie.getReleaseDate());
  }

  public static JsonMovie toJson(Movie movie) {
    if (movie == null) {
      return null;
    }

    JsonMovie jsonMovie = new JsonMovie();

    applyBasicJsonValues(jsonMovie, movie);

    Genre genre = movie.getGenre();
    if (genre != null) {
      jsonMovie.setGenre(toBasicJson(genre));
    }

    List<Actor> listElenco = movie.getCast();
    if (listElenco != null) {
      for (Actor loopActor : listElenco) {
        jsonMovie.getCast().add(toBasicJson(loopActor));
      }
    }

    return jsonMovie;
  }

  public static Movie apply(Movie movie, JsonMovie jsonMovie) {

    if (movie == null) movie = new Movie();

    applyBasicEntityValues(movie, jsonMovie);

    JsonGenre genre = jsonMovie.getGenre();
    if (genre != null) {
      movie.setGenre(toBasicEntity(genre));
    }
    return movie;
  }

  public static Movie toEntity(JsonMovie jsonMovie) {
    Movie movie = new Movie();

    return apply(movie, jsonMovie);
  }

  public static List<JsonMovie> toListJsonMovies(List<Movie> all) {
    List<JsonMovie> jsonMovies = new ArrayList<JsonMovie>();
    for (Movie movie : all) {
      jsonMovies.add(toJson(movie));
    }
    return jsonMovies;
  }

  // converte de entidade para json --------------------
  private static JsonActor toBasicJson(Actor actor) {
    JsonActor jsonActor = new JsonActor();
    applyBasicJsonValues(jsonActor, actor);
    return jsonActor;
  }

  private static Actor toBasicEntity(JsonActor jsonActor) {
    Actor actor = new Actor();
    applyBasicEntityValues(actor, jsonActor);
    return actor;
  }

  private static void applyBasicJsonValues(JsonActor jsonActor, Actor actor) {
    jsonActor.setId(actor.getId());
    jsonActor.setName(actor.getName());
  }

  private static void applyBasicEntityValues(Actor actor, JsonActor jsonActor) {
    actor.setId(jsonActor.getId());
  }

  public static JsonActor toJson(Actor actor) {
    if (actor == null) {
      return null;
    }

    JsonActor jsonActor = new JsonActor();

    applyBasicJsonValues(jsonActor, actor);

    List<Movie> listMovies = actor.getWork();
    if (listMovies != null) {
      for (Movie loopMovie : listMovies) {
        jsonActor.getWork().add(toBasicJson(loopMovie));
      }
    }

    return jsonActor;
  }

  public static Actor apply(Actor actor, JsonActor jsonActor) {

    if (actor == null) actor = new Actor();

    applyBasicEntityValues(actor, jsonActor);

    ArrayList<JsonMovie> listMovies = jsonActor.getWork();
    if (listMovies != null) {
      for (JsonMovie loopJsonMovie : listMovies) {
        actor.getWork().add(toBasicEntity(loopJsonMovie));
      }
    }

    return actor;
  }

  public static Actor toEntity(JsonActor jsonActor) {
    Actor actor = new Actor();

    return apply(actor, jsonActor);
  }

  public static List<JsonActor> toListJsonActors(List<Actor> all) {
    List<JsonActor> jsonActors = new ArrayList<JsonActor>();
    for (Actor actor : all) {
      jsonActors.add(toJson(actor));
    }
    return jsonActors;
  }
}
