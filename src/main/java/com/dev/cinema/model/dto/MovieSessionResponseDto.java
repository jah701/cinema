package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long movieId;
    private Long cinemaHallId;
    private String showtime;
}
