package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieRequestDto {
    @NotNull(message = "Title can not be null")
    @Size(min = 5, max = 20)
    private String title;
    @Size(min = 10, max = 500)
    @NotNull(message = "Description can not be null")
    private String description;
}
