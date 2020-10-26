package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto orderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setUserId(order.getUser().getId());
        dto.setTickets(order.getTickets().stream()
                .map(ticketMapper::ticketToDto)
                .collect(Collectors.toList()));
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
