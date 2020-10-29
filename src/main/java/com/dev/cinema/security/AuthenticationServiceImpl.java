package com.dev.cinema.security;

import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(ShoppingCartService shoppingCartService,
                                     UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @Override
    public User register(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;

    }
}
