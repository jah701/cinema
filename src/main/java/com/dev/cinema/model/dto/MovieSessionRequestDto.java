package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieSessionRequestDto {
    @NotNull(message = "Movie id can not be null")
    private Long movieId;
    @NotNull(message = "Cinema hall id can not be null")
    private Long cinemaHallId;
    @NotNull(message = "Showtime can not be null")
    private LocalDateTime showtime;
}
