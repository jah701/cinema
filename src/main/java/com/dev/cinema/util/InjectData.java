package com.dev.cinema.util;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public InjectData(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    void inject() {
        Role userRole = Role.of("USER");
        Role adminRole = Role.of("ADMIN");
        roleService.add(userRole);
        roleService.add(adminRole);

        User bob = new User();
        bob.setName("Bob");
        bob.setLogin("Bob123");
        bob.setEmail("bob@mail.com");
        bob.setPassword("1234");
        bob.setRoles(Set.of(roleService.getByName("ADMIN")));
        System.out.println(userService.add(bob) + "[ADDED]");

        User alice = new User();
        alice.setName("Alice");
        alice.setLogin("Alice123");
        alice.setPassword("1234");
        alice.setEmail("alice@mail.com");
        alice.setRoles(Set.of(roleService.getByName("USER")));
        System.out.println(userService.add(alice) + "[ADDED]");
    }
}
