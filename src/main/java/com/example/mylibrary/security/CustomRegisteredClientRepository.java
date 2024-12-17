package com.example.mylibrary.security;

import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

import com.example.mylibrary.service.ClientService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository {
    private final ClientService service;

    @Override
    @Nullable
    public RegisteredClient findByClientId(String clientId) {
        var client = service.findById(clientId);
        if (client == null) {
            return null;
        }
        return RegisteredClient.withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .redirectUri(client.getRedirectURI())
                .scope(client.getScope())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();
    }

    @Override
    @Nullable
    public RegisteredClient findById(String arg0) {
        return null;
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

}
