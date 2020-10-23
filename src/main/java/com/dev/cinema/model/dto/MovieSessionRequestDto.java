package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class MovieSessionRequestDto {
    private Long id;
    private Long movieId;
    private Long cinemaHallId;
    private String showtime;
}
