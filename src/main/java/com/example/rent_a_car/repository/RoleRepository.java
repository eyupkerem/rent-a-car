package com.example.rent_a_car.repository;

import com.example.rent_a_car.entity.Role;
import com.example.rent_a_car.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {
    Optional<Role> findByRole(ERole role);
}
