package com.dev.cinema.security;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    @Autowired
    public CustomUserDetailsService(UserDao userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username).orElseThrow();
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(encoder.encode(user.getPassword()));
            String[] roles = user.getRoles()
                    .stream()
                    .map(r -> r.getRole().toString())
                    .toArray(String[]::new);
            builder.roles(roles);
        } else {
            throw new UsernameNotFoundException("User with email: '"
                    + username + "' was not found.");
        }
        return builder.build();
    }
}
