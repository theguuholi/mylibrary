package com.example.mylibrary.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.example.mylibrary.model.Autor;
import com.example.mylibrary.repository.AutorRepository;
import com.example.mylibrary.security.SecurityService;
import com.example.mylibrary.validator.AutorValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator validator;
    private final SecurityService securityService;

    // public AutorService(AutorRepository repository, AutorValidator validator) {
    // this.repository = repository;
    // this.validator = validator;
    // }

    public Autor salvar(Autor autor) {
        validator.validate(autor);
        var user = securityService.getLoggedUser();
        // autor.setUserId(user.getId());
        autor.setUser(user);
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
        if (result.getId() == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return repository.save(result);
    }

    // public boolean possuiLivro(Autor autor) {
    // return false;
    // }

    public List<Autor> search(String nome, String nacionalidade) {
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        var matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        var exampleAutor = Example.of(autor, matcher);
        return repository.findAll(exampleAutor);
    }

}
