package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        byte[] salt = userFromDb.get().getSalt();
        String saltedPass = HashUtil.hashPassword(password, salt);
        if (userFromDb != null && saltedPass.equals(userFromDb.get().getPassword())) {
            return userFromDb.get();
        }
        throw new AuthenticationException("Incorrect data entered");
    }

    @Override
    public User register(String email, String password) {
        return userService.add(new User(password, email));
    }
}
