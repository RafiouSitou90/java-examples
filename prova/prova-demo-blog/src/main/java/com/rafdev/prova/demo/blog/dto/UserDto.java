package com.rafdev.prova.demo.blog.dto;

import com.rafdev.prova.demo.blog.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private List<String> roles;

    public UserDto(Long id, String username, String email, String fullName, List<Role> roles) {
        List<String> strRoles = new ArrayList<>();
        roles.forEach(role -> {
            strRoles.add(role.getName().toString());
        });

        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.roles = strRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
