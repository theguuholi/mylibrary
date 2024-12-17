package com.example.mylibrary.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mylibrary.model.Client;
import com.example.mylibrary.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client save(Client client) {
        client.setClientSecret(encoder.encode(client.getClientSecret()));

        return repository.save(client);
    }

    public Client findById(String id) {
        return repository.findByClientId(id);
    }
}
