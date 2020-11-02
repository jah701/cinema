package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @Size(min = 25, message = "Minimum capacity is 25")
    private int capacity;
    @NotNull(message = "Description can not be null")
    private String description;
}
