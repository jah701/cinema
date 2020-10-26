package com.dev.cinema.model.dto;

import com.dev.cinema.model.Ticket;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartRequestDto {
    private Long id;
    private List<Ticket> tickets;
}
