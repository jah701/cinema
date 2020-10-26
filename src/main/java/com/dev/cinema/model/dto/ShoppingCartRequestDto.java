package com.dev.cinema.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    private Long id;
    private List<TicketResponseDto> tickets;
}
