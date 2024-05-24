package com.keyclock.authentication.controller;

import com.keyclock.authentication.model.UserRegistrationRequest;
import com.nimbusds.oauth2.sdk.AccessTokenResponse;
import jakarta.ws.rs.core.Response;
import net.minidev.json.JSONObject;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.account.UserRepresentation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/user")
public class LoginController {

    private final Keycloak keycloak;

    public LoginController(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserRegistrationRequest user) {
        try {
            AccessTokenResponse tokenResponse = keycloak.realm("abc").users().get(user.getUsername())


            ResponseEntity.ok(tokenResponse.getToken());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @GetMapping("/login")
    public String loginUser() {
        return "redirect:http://localhost:8080/auth/realms/abc/protocol/openid-connect/auth?client_id=login-app&" + "redirect_uri=http://localhost:8080/api/v1/user/callback&response_type=code";
    }


    @GetMapping("/callback")
    public String callbackUser(@RequestParam("code") String code) {
        RestTemplate restTemplate = new RestTemplate();
        String tokenResponse = restTemplate.postForObject("http://localhost:8080/auth/realms/abc/protocol/openid-connect/token", new HttpEntity<>(new HttpHeaders()), String.class);
        System.out.println(tokenResponse);
        String accessToken = new JSONObject(tokenResponse).getString("access_token");
        return "Logged in successfully";
    }

}
