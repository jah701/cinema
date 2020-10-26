package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.model.dto.ShoppingCartRequestDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           UserService userService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public Order completeOrder(@RequestBody ShoppingCartRequestDto dto) {
        return orderService.completeOrder(dto.getTickets(), userService.getById(dto.getId()).get());
    }

    @GetMapping
    public List<OrderResponseDto> getAllUserOrders(@RequestParam Long userId) {
        User user = userService.getById(userId).get();
        return orderService.getOrderHistory(user).stream()
                .map(orderMapper::orderToDto)
                .collect(Collectors.toList());
    }
}
