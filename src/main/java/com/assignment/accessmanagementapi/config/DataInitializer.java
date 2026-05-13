package com.assignment.accessmanagementapi.config;

import com.assignment.accessmanagementapi.entity.Role;
import com.assignment.accessmanagementapi.entity.User;
import com.assignment.accessmanagementapi.repository.RoleRepository;
import com.assignment.accessmanagementapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isEmpty()) {

            Role adminRole = new Role("ROLE_ADMIN");

            roleRepository.save(adminRole);

            User admin = new User();

            admin.setUsername("admin");

            admin.setPassword(
                    passwordEncoder.encode("admin123")
            );

            admin.setRoles(
                    Set.of(adminRole)
            );

            userRepository.save(admin);

            System.out.println("ADMIN USER CREATED");
        }

        if (userRepository.findByUsername("user").isEmpty()) {

            Role userRole = new Role("ROLE_USER");

            roleRepository.save(userRole);

            User normalUser = new User();

            normalUser.setUsername("user");

            normalUser.setPassword(
                    passwordEncoder.encode("user123")
            );

            normalUser.setRoles(
                    Set.of(userRole)
            );

            userRepository.save(normalUser);

            System.out.println("USER CREATED");
        }
    }
}