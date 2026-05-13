package com.assignment.accessmanagementapi.service;

import com.assignment.accessmanagementapi.dto.LoginRequest;
import com.assignment.accessmanagementapi.dto.LoginResponse;
import com.assignment.accessmanagementapi.entity.User;
import com.assignment.accessmanagementapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder
    ) {

        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse authenticate(
            LoginRequest request
    ) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password")
                );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new RuntimeException(
                    "Invalid username or password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getUsername()
                );

        return new LoginResponse(token);
    }
}