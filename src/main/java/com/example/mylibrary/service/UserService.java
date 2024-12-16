package com.example.mylibrary.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mylibrary.model.User;
import com.example.mylibrary.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public User save(User user) {
        var pw = encoder.encode(user.getPassword());
        user.setPassword(pw);
        return repository.save(user);
    }

    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }

}
