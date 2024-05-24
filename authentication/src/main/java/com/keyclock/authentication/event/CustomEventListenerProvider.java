package com.keyclock.authentication.event;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent
        ;
import org.keycloak.models.KeycloakSession;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CustomEventListenerProvider implements EventListenerProvider {

    private final KeycloakSession keycloakSession;
    private final RestTemplate restTemplate = new RestTemplate();

    public CustomEventListenerProvider(KeycloakSession keycloakSession) {
        this.keycloakSession = keycloakSession;
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals(EventType.REMOVE_FEDERATED_IDENTITY) && event.getType().equals("remove_federated_identity")) {
            String resourceServerUrl = "http://localhost:8080/api/v1/user-deleted";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestBody = "{\"user_id\": \"" + event.getUserId() + "\"}";
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    resourceServerUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("User deleted event propagated to resource server");
            } else {
                System.out.println("User deleted event propagation failed");
            }
        }
    }

}

