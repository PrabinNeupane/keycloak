package com.keyclock.authentication.service;

import com.keyclock.authentication.model.UserLogin;
import com.keyclock.authentication.model.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakService {

    @Value("${keycloak.server.url")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    private final RestTemplate restTemplate;


    public KeycloakService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public void registerUser(UserRegistrationRequest registrationRequest) {
        String registrationUrl = keycloakServerUrl + "/auth/realms/" + realm + "/broker/keycloak/registration";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserRegistrationRequest> request = new HttpEntity<>(registrationRequest, headers);

        restTemplate.postForEntity(registrationUrl, request, String.class);
    }

    public void loginUser(UserLogin loginRequest) {
        String tokenUrl = keycloakServerUrl + "/auth/realms/" + realm + "/protocol/openid-connect/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", "login-app");
        formData.add("username", loginRequest.getUsername());
        formData.add("password", loginRequest.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        restTemplate.postForEntity(tokenUrl, request, String.class);
    }
}
