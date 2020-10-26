package com.dev.cinema.model.dto;

import com.dev.cinema.model.Ticket;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequestDto {
    private List<Ticket> tickets;
    private LocalDateTime orderDate;
}
