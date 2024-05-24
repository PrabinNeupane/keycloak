package com.keyclock.authentication.controller;

import com.keyclock.authentication.model.UserLogin;
import com.keyclock.authentication.service.KeycloakService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginControllerv1 {


    private final KeycloakService keycloakService;

    public LoginControllerv1(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody UserLogin loginRequest) {
        keycloakService.loginUser(loginRequest);
        return ResponseEntity.ok("Login successful");
    }
}
