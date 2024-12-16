package com.example.mylibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.mylibrary.controller.dto.UserDTO;
import com.example.mylibrary.controller.mappers.UserMapper;
import com.example.mylibrary.model.User;
import com.example.mylibrary.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody UserDTO dto) {
        var entity = mapper.toEntity(dto);
        return service.save(entity);
    }
}
