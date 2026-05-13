package com.assignment.accessmanagementapi.controller;

import com.assignment.accessmanagementapi.dto.LoginRequest;
import com.assignment.accessmanagementapi.dto.LoginResponse;
import com.assignment.accessmanagementapi.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(
            AuthenticationService authenticationService
    ) {

        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        return authenticationService
                .authenticate(request);
    }
}