package com.keyclock.authentication.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {


    @Bean
    public Keycloak keycloak() {
        ResteasyClientBuilder resteasyClientBuilder = new ResteasyClientBuilderImpl();
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/realms/abc/protocol/openid-connect/auth")
                .realm("abc")
                .username("admin")
                .password("admin")
                .clientId("login-app")
                .resteasyClient(resteasyClientBuilder.connectionPoolSize(10).build())
                .build();
    }
}
