package com.assignment.accessmanagementapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {

        return "Welcome Admin";
    }

    @GetMapping("/user/profile")
    public String userProfile() {

        return "Welcome User";
    }
}