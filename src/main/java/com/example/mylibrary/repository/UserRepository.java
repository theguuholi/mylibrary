package com.example.mylibrary.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mylibrary.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByLogin(String login);
    
}
