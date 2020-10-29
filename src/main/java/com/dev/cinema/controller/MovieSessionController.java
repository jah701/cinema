package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-session")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionMapper movieSessionMapper,
                                  MovieSessionService movieSessionService) {
        this.movieSessionMapper = movieSessionMapper;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping
    public MovieSessionResponseDto add(@RequestBody @Valid MovieSessionRequestDto dto) {
        MovieSession movieSession = movieSessionService.add(
                movieSessionMapper.dtoToMovieSession(dto));
        return movieSessionMapper.movieSessionToDto(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableSession(
            @RequestParam Long movieId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDate showtime) {
        return movieSessionService.findAvailableSessions(movieId, showtime)
                .stream()
                .map(movieSessionMapper::movieSessionToDto)
                .collect(Collectors.toList());
    }
}
