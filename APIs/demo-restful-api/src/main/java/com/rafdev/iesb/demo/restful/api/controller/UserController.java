package com.rafdev.iesb.demo.restful.api.controller;

import com.rafdev.iesb.demo.restful.api.entity.user.User;
import com.rafdev.iesb.demo.restful.api.entity.user.UserPage;
import com.rafdev.iesb.demo.restful.api.payload.request.SignUpRequest;
import com.rafdev.iesb.demo.restful.api.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return new ResponseEntity<>(userService.saveUser(signUpRequest), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<User>> getUsers(UserPage userPage) {
        return new ResponseEntity<>(userService.getUsers(userPage), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id,
                                            @Valid @RequestBody SignUpRequest signUpRequest) {
        return new ResponseEntity<>(userService.updateUserById(id, signUpRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);

        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }
}
