package com.dev.cinema.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class OrderResponseDto {
    @NonNull
    private Long userId;
    @NonNull
    private Long orderId;
    @NonNull
    private List<Long> tickets;
    @NonNull
    private LocalDateTime orderDate;
}
