package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        byte[] userSalt = HashUtil.getSalt();
        String hashedPass = HashUtil.hashPassword(user.getPassword(), userSalt);
        user.setPassword(hashedPass);
        user.setSalt(userSalt);
        userDao.add(user);
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
