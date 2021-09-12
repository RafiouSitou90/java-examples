package com.rafdev.prova.demo.blog.repository;

import com.rafdev.prova.demo.blog.entity.ERole;
import com.rafdev.prova.demo.blog.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleRepository {

    private final List<Role> roles;

    public RoleRepository() {
        List<Role> listRoles = new ArrayList<>();

        listRoles.add(new Role(1L, ERole.ROLE_USER));
        listRoles.add(new Role(2L, ERole.ROLE_ADMIN));
        listRoles.add(new Role(3L, ERole.ROLE_SUPER_ADMIN));

        this.roles = listRoles;
    }

    public List<Role> findAll() {
        return roles;
    }

    public Role findByName(ERole name) {
        for (Role role: roles) {
            if (role.getName().equals(name)) {
                return role;
            }
        }

        return null;
    }
}
