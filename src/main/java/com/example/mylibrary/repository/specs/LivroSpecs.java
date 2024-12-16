package com.example.mylibrary.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.example.mylibrary.model.GeneroLivro;
import com.example.mylibrary.model.Livro;

public class LivroSpecs {
    public static Specification<Livro> generoEqual(GeneroLivro genero) {
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);
    }

    public static Specification<Livro> isbnEqual(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }
}
