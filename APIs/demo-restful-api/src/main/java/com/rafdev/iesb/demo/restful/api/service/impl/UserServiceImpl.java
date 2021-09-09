package com.rafdev.iesb.demo.restful.api.service.impl;

import com.rafdev.iesb.demo.restful.api.entity.user.ERole;
import com.rafdev.iesb.demo.restful.api.entity.user.Role;
import com.rafdev.iesb.demo.restful.api.entity.user.User;
import com.rafdev.iesb.demo.restful.api.entity.user.UserPage;
import com.rafdev.iesb.demo.restful.api.exception.ResourceAlreadyExistsException;
import com.rafdev.iesb.demo.restful.api.exception.ResourceNotFoundException;
import com.rafdev.iesb.demo.restful.api.payload.request.SignUpRequest;
import com.rafdev.iesb.demo.restful.api.repository.RoleRepository;
import com.rafdev.iesb.demo.restful.api.repository.UserRepository;
import com.rafdev.iesb.demo.restful.api.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final String resourceName = "User";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public User saveUser(SignUpRequest signUpUser) throws ResourceAlreadyExistsException {
        if (userRepository.existsByUsername(signUpUser.getUsername())) {
            throw new ResourceAlreadyExistsException(resourceName, "username", signUpUser.getUsername());
        }

        if (userRepository.existsByEmail(signUpUser.getEmail())) {
            throw new ResourceAlreadyExistsException(resourceName, "email", signUpUser.getEmail());
        }

        User user = new User(signUpUser.getUsername(), signUpUser.getEmail(), encoder.encode(signUpUser.getPassword()));
        Set<String> strRoles = signUpUser.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "super_admin" -> {
                        Role superAdminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(superAdminRole);
                    }
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(UserPage userPage) {
        Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy());
        Pageable pageable = PageRequest.of(userPage.getPageNumber(), userPage.getPageSize(), sort);

        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));
    }

    @Override
    public User updateUserById(Long id, SignUpRequest userUpdated) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));

        userToUpdate.setUsername(userUpdated.getUsername());
        userToUpdate.setEmail(userUpdated.getEmail());
        userToUpdate.setPassword(encoder.encode(userUpdated.getPassword()));

        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));

        userRepository.deleteById(user.getId());
    }
}
