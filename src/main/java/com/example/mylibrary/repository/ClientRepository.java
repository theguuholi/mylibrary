package com.example.mylibrary.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mylibrary.model.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client findByClientId(String clientId);

}
