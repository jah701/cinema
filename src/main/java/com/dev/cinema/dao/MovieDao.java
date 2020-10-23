package com.dev.cinema.dao;

import com.dev.cinema.model.Movie;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
    Movie add(Movie movie);

    Optional<Movie> getById(Long id);

    List<Movie> getAll();
}
