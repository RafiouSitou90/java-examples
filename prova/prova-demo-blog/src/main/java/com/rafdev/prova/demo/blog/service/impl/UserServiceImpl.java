package com.rafdev.prova.demo.blog.service.impl;

import com.rafdev.prova.demo.blog.dto.UserDTO;
import com.rafdev.prova.demo.blog.entity.ERole;
import com.rafdev.prova.demo.blog.entity.Role;
import com.rafdev.prova.demo.blog.entity.User;
import com.rafdev.prova.demo.blog.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.demo.blog.exception.ResourceNotFoundException;
import com.rafdev.prova.demo.blog.payload.request.UserCreationRequest;
import com.rafdev.prova.demo.blog.repository.RoleRepository;
import com.rafdev.prova.demo.blog.repository.UserRepository;
import com.rafdev.prova.demo.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepository();
        this.roleRepository = new RoleRepository();
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> usersDto = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user: users) {
            String fullName = user.getFirstName() + " " + user.getLastName();
            UserDTO userDto = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), fullName, user.getRoles());

            usersDto.add(userDto);
        }

        return usersDto;
    }

    @Override
    public UserDTO saveUser(UserCreationRequest userRequest) throws ResourceAlreadyExistsException {
        if (userRepository.existsById(userRequest.getId())) {
            throw new ResourceAlreadyExistsException("User", "Id", userRequest.getId());
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResourceAlreadyExistsException("User", "Email", userRequest.getEmail());
        }

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new ResourceAlreadyExistsException("User", "Username", userRequest.getUsername());
        }

        List<Role> roles = setUserRoles(userRequest.getRoles());

        User user = new User(userRequest.getId(), userRequest.getUsername(), userRequest.getEmail(),
                userRequest.getPassword(), userRequest.getFirstName(), userRequest.getLastName(), roles);

        User userCreated = userRepository.save(user);

        return setUserDTO(userCreated);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User", "Id", id);
        }

        return setUserDTO(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User", "Id", id);
        }

        userRepository.delete(id);
    }

    @Override
    public UserDTO updateUserById(Long id, UserCreationRequest userRequest) {
        User userFound = userRepository.findById(id);

        if (userFound == null) {
            throw new ResourceNotFoundException("User", "Id", id);
        }

        List<Role> roles = setUserRoles(userRequest.getRoles());

        User user = new User(userRequest.getId(), userRequest.getUsername(), userRequest.getEmail(),
                userRequest.getPassword(), userRequest.getFirstName(), userRequest.getLastName(), roles);

        userFound.setUsername(user.getUsername());
        userFound.setEmail(user.getEmail());
        userFound.setPassword(user.getPassword());
        userFound.setFirstName(user.getFirstName());
        userFound.setLastName(user.getLastName());
        userFound.setRoles(user.getRoles());

        User userUpdated = userRepository.update(userFound);

        return setUserDTO(userUpdated);
    }

    private UserDTO setUserDTO(User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), fullName, user.getRoles());
    }

    private List<Role> setUserRoles(List<String> strRoles) {
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "super_admin" -> {
                        Role superAdminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
                        roles.add(superAdminRole);
                    }
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                    }
                }
            });
        }

        return roles;
    }
}
