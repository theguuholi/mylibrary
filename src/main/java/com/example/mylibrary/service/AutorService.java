package com.example.mylibrary.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.mylibrary.model.Autor;
import com.example.mylibrary.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }

    public Autor getById(String id) {
        return repository.getReferenceById(UUID.fromString(id));
    }

    public void deleteById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

}
