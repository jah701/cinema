package com.dev.cinema.security;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't get user wth email: "
                        + username));
        UserBuilder builder
                = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        String[] roles = user.getRoles().stream()
                .map(r -> r.getRole().toString())
                .toArray(String[]::new);
        builder.roles(roles);
        return builder.build();
    }
}
