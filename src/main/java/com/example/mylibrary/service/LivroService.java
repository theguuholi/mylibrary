package com.example.mylibrary.service;

import org.springframework.stereotype.Service;

import com.example.mylibrary.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository repository;
}
