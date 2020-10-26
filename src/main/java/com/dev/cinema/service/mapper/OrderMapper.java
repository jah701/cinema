package com.dev.cinema.service.mapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.model.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order dtoToOrder(OrderRequestDto dto) {
        Order order = new Order();
        order.setTickets(dto.getTickets());
        order.setOrderDate(dto.getOrderDate());
        return order;
    }

    public OrderResponseDto orderToDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setTickets(order.getTickets());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
