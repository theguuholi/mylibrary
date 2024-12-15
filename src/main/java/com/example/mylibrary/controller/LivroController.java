package com.example.mylibrary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mylibrary.controller.dto.CadastroLivroDTO;
import com.example.mylibrary.controller.dto.ErrorResponse;
import com.example.mylibrary.exceptions.DuplicatedRegistryException;
import com.example.mylibrary.service.LivroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid CadastroLivroDTO dto) {
        try {
            return null;
        } catch (DuplicatedRegistryException e) {
            var errorDTO = ErrorResponse.defaultResponse(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }

}
