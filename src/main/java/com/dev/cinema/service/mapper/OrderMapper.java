package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto orderToDto(Order order) {
        return new OrderResponseDto(
                order.getUser().getId(),
                order.getId(),
                order.getTickets().stream()
                        .map(Ticket::getId)
                        .collect(Collectors.toList()),
                order.getOrderDate()
        );
    }
}
