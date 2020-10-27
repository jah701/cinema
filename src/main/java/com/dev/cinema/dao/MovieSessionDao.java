package com.dev.cinema.dao;

import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    List<MovieSession> getAvailableMovieSessions(Long id, LocalDate date);

    Optional<MovieSession> getById(Long id);

    MovieSession add(MovieSession movieSession);
}
