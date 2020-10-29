package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class MovieSessionRequestDto {
    @NonNull
    private Long movieId;
    @NonNull
    private Long cinemaHallId;
    @NonNull
    private LocalDateTime showtime;
}
