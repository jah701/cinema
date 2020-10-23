package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.mapper.MovieSessionMapper;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie-session")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionMapper movieSessionMapper, MovieSessionService movieSessionService) {
        this.movieSessionMapper = movieSessionMapper;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping
    public MovieSession add(@RequestBody MovieSessionRequestDto dto) {
        return movieSessionService.add(movieSessionMapper.dtoToMovieSession(dto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableSession(@RequestParam Long id, @RequestParam LocalDate showtime) {
        return movieSessionService.findAvailableSessions(id, showtime)
                .stream()
                .map(movieSessionMapper::movieSessionToDto)
                .collect(Collectors.toList());
    }
}
