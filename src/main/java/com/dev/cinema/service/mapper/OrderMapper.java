package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto orderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTickets(order.getTickets()
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toList()));
        return dto;
    }
}
