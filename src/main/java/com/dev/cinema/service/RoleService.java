package com.dev.cinema.service;

import com.dev.cinema.model.Role;

public interface RoleService {
    void add(Role role);

    Role getByName(String roleName);
}
