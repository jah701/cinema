package com.dev.cinema.model.dto;

import lombok.Data;

@Data
public class TicketResponseDto {
    private Long id;
    private MovieSessionResponseDto movieSessionResponseDto;
    private Long userId;
}
