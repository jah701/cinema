package com.dev.cinema.model.dto;

import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class MovieRequestDto {
    @NonNull
    @Size(min = 5, max = 20)
    private String title;
    @Size(min = 10, max = 500)
    private String description;
}
