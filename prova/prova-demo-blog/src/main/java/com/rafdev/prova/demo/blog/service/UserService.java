package com.rafdev.prova.demo.blog.service;

import com.rafdev.prova.demo.blog.dto.UserDto;
import com.rafdev.prova.demo.blog.payload.request.UserCreationRequest;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto saveUser(UserCreationRequest userRequest);

    UserDto getUserById(Long id);

    void deleteUserById(Long id);

    UserDto updateUserById(Long id, UserCreationRequest userRequest);
}
