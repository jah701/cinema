package com.dev.cinema.service.mapper;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSessionResponseDto movieSessionToDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setMovieId(movieSession.getMovie().getId());
        dto.setShowtime(movieSession.getShowtime()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return dto;
    }

    public MovieSession dtoToMovieSession(MovieSessionRequestDto dto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(dto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getById(dto.getCinemaHallId()));
        movieSession.setShowtime(LocalDate.parse(dto.getShowtime()));
        return movieSession;
    }
}
