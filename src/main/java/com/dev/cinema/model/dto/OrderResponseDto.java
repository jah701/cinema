package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long userId;
    private Long orderId;
    private List<Long> tickets;
    private LocalDateTime orderDate;
}
