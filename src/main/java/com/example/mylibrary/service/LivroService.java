package com.example.mylibrary.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.mylibrary.model.GeneroLivro;
import com.example.mylibrary.model.Livro;
import com.example.mylibrary.repository.LivroRepository;
import com.example.mylibrary.repository.specs.LivroSpecs;
import com.example.mylibrary.validator.LivroValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository repository;
    private final LivroValidator validator;

    public Livro save(Livro livro) {
        validator.validate(livro);
        return repository.save(livro);
    }

    public Optional<Livro> get(UUID id) {
        return this.repository.findById(id);
    }

    public void delete(Livro livro) {
        this.repository.delete(livro);
    }

    public Page<Livro> search(String isbn, String titulo, GeneroLivro genero, Integer anoPublicacao,
            Integer page, Integer size) {
        // Specification<Livro> spec = Specification.where(
        // LivroSpecs.isbnEqual(isbn)).and(LivroSpecs.tituloLike(titulo))
        // .and(LivroSpecs.generoEqual(genero));

        // select * from livro where 0 = 0;
        Specification<Livro> spec = Specification.where((_, _, c) -> c.conjunction());

        if (isbn != null) {
            spec = spec.and(LivroSpecs.isbnEqual(isbn));
        }

        if (titulo != null) {
            spec = spec.and(LivroSpecs.tituloLike(titulo));
        }

        if (genero != null) {
            spec = spec.and(LivroSpecs.generoEqual(genero));
        }

        return this.repository.findAll(spec, PageRequest.of(page, size));
    }
}
