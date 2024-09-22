package com.example.rent_a_car.utils.database;

import com.example.rent_a_car.entity.Role;
import com.example.rent_a_car.entity.enums.ERole;
import com.example.rent_a_car.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
    }
    private void loadRoles() {
        for (ERole eRole : ERole.values()) {
            if (roleRepository.findByRole(eRole).isEmpty()) {
                Role role = new Role();
                role.setRole(eRole);
                roleRepository.save(role);
            }
        }
    }



}
