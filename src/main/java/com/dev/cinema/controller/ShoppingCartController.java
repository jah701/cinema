package com.dev.cinema.controller;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.ShoppingCartResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.mapper.ShoppingCartMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        User user = userService.getById(userId);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        return shoppingCartMapper.cartToDto(shoppingCart);
    }

    @PostMapping("/movie-sessions")
    public void addMovieSessionToShoppingCart(@RequestParam Long userId,
                                                     @RequestParam Long sessionId) {
        MovieSession movieSession = movieSessionService.getById(sessionId);
        User user = userService.getById(userId);
        shoppingCartService.addSession(movieSession, user);
    }
}
