package com.example.mylibrary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mylibrary.service.LivroService;

import lombok.RequiredArgsConstructor;

@RestController
// @RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {
    
    private final LivroService service;
    
}
