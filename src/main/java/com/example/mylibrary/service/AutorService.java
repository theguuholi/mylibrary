package com.example.mylibrary.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.mylibrary.model.Autor;
import com.example.mylibrary.repository.AutorRepository;
import com.example.mylibrary.validator.AutorValidator;

@Service
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator validator;

    public AutorService(AutorRepository repository, AutorValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Autor salvar(Autor autor) {
        validator.validate(autor);
        return repository.save(autor);
    }

    public Autor getById(String id) {
        return repository.getReferenceById(UUID.fromString(id));
    }

    public void deleteById(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    public Autor update(Autor result) {
        validator.validate(result);
        if(result.getId() == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return repository.save(result);
    }

    // public List<Autor> search(String nome, String nacionalidade) {
    //     return repository.search(nome, nacionalidade);
    // }

}
