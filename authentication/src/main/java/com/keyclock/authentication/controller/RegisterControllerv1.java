package com.keyclock.authentication.controller;

import com.keyclock.authentication.model.UserRegistrationRequest;
import com.keyclock.authentication.service.KeycloakService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterControllerv1 {


    private final KeycloakService keycloakService;

    public RegisterControllerv1(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        keycloakService.registerUser(registrationRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}
