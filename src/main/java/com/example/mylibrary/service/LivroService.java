package com.example.mylibrary.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.mylibrary.model.GeneroLivro;
import com.example.mylibrary.model.Livro;
import com.example.mylibrary.repository.LivroRepository;
import com.example.mylibrary.repository.specs.LivroSpecs;

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

    public void delete(Livro livro) {
        this.repository.delete(livro);
    }

    public List<Livro> search(String isbn, String titulo, GeneroLivro genero, Integer anoPublicacao) {
        // Specification<Livro> spec = Specification.where(
        // LivroSpecs.isbnEqual(isbn)).and(LivroSpecs.tituloLike(titulo))
        // .and(LivroSpecs.generoEqual(genero));

        // select * from livro where 0 = 0;
        Specification<Livro> spec = Specification.where((r, q, c) -> c.conjunction());

        if (isbn != null) {
            spec = spec.and(LivroSpecs.isbnEqual(isbn));
        }

        if (titulo != null) {
            spec = spec.and(LivroSpecs.tituloLike(titulo));
        }

        if (genero != null) {
            spec = spec.and(LivroSpecs.generoEqual(genero));
        }

        return this.repository.findAll(spec);
    }
}
