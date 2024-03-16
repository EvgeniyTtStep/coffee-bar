package com.example.coffeebar.repository;

import com.example.coffeebar.entity.ERole;
import com.example.coffeebar.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(ERole eRole);
}
