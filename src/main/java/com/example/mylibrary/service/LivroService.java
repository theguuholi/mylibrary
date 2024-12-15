package com.example.mylibrary.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.mylibrary.model.Livro;
import com.example.mylibrary.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository repository;

    public Livro save(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> get(UUID id) {
        return this.repository.findById(id);
    }
}
