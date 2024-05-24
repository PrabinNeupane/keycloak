package com.keyclock.authentication.controller;


import com.keyclock.authentication.config.KeycloakConfig;
import com.keyclock.authentication.model.UserRegistrationRequest;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {


    private final KeycloakConfig keycloakConfig;

    public RegistrationController(KeycloakConfig keycloakConfig) {

        this.keycloakConfig = keycloakConfig;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {

        RealmResource realmResource = keycloakConfig.keycloak().realm("abc");
        UserRepresentation user = new UserRepresentation();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        Response response = realmResource.users().create(user);

        if (response.getStatus() == 201) {
            return ResponseEntity.ok("User registered successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed.");
        }
    }
}
