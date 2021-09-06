package com.rafdev.iesb.demo.restful.api.repository;

import com.rafdev.iesb.demo.restful.api.entity.user.ERole;
import com.rafdev.iesb.demo.restful.api.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
