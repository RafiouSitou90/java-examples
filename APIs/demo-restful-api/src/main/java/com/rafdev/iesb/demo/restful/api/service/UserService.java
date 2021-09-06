package com.rafdev.iesb.demo.restful.api.service;

import com.rafdev.iesb.demo.restful.api.entity.user.User;
import com.rafdev.iesb.demo.restful.api.entity.user.UserPage;
import com.rafdev.iesb.demo.restful.api.payload.request.SignUpRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    User saveUser(SignUpRequest signUpUser);

    Page<User> getUsers(UserPage userPage);

    User getUserById(Long id);

    User updateUserById(Long id, SignUpRequest userUpdated);

    void deleteUserById(Long id);
}
