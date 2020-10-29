package com.dev.cinema.model.dto;

import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class CinemaHallRequestDto {
    @NonNull
    @Size(min = 25)
    private int capacity;
    private String description;
}
