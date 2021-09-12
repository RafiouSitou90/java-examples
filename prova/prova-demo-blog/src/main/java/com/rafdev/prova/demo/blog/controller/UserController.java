package com.rafdev.prova.demo.blog.controller;

import com.rafdev.prova.demo.blog.dto.UserDto;
import com.rafdev.prova.demo.blog.exception.ResourceNotFoundException;
import com.rafdev.prova.demo.blog.payload.request.UserCreationRequest;
import com.rafdev.prova.demo.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserCreationRequest userRequest) {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Long id, @RequestBody UserCreationRequest userRequest)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.updateUserById(id, userRequest), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        userService.deleteUserById(id);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
