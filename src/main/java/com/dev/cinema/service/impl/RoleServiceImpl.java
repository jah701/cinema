package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role getByName(String roleName) {
        return roleDao.getByName(roleName);
    }
}
