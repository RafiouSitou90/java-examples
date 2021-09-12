package com.rafdev.prova.demo.blog.service;

import com.rafdev.prova.demo.blog.dto.UserDTO;
import com.rafdev.prova.demo.blog.payload.request.UserCreationRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    UserDTO saveUser(UserCreationRequest userRequest);

    UserDTO getUserById(Long id);

    void deleteUserById(Long id);

    UserDTO updateUserById(Long id, UserCreationRequest userRequest);
}
