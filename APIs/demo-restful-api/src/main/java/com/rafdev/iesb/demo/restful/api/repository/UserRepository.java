package com.rafdev.iesb.demo.restful.api.repository;

import com.rafdev.iesb.demo.restful.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsernameAndEmail(String username, String email);
}
