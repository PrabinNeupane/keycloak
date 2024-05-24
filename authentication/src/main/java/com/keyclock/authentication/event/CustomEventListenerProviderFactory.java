package com.keyclock.authentication.event;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class CustomEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new CustomEventListenerProvider(session);
    }

    @Override
    public void init(Config.Scope config) {
//need to complete
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
//need to complete

    }

    @Override
    public void close() {
//need to complete

    }

    @Override
    public String getId() {
        return null;
    }
}
