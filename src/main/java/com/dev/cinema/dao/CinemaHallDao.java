package com.dev.cinema.dao;

import com.dev.cinema.model.CinemaHall;
import java.util.List;
import java.util.Optional;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    Optional<CinemaHall> getById(Long id);

    List<CinemaHall> getAll();
}
