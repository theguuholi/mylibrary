package com.example.mylibrary.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.mylibrary.exceptions.CampoInvalidoException;
import com.example.mylibrary.exceptions.DuplicatedRegistryException;
import com.example.mylibrary.model.Livro;
import com.example.mylibrary.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LivroValidator {
    private final LivroRepository repository;
    private static final int ANO_EXIGENCIA_PRECO = 2020;

    public void validate(Livro livro) {
        if (isbnJaCadastrado(livro)) {
            throw new DuplicatedRegistryException("ISBN já cadastrado");
        }

        if(isPrecoObrigatorioNulo(livro)) {
            throw new CampoInvalidoException("preco", "para livros com ano de publicacao a partir de 2020 o preco e obrigatorio");
        }

    }

    private boolean isbnJaCadastrado(Livro livro) {
        Optional<Livro> result = repository.findByIsbn(livro.getIsbn());

        if (livro.getId() == null) {
            return result.isPresent();
        }
        return result.map(Livro::getId).stream().anyMatch(id -> !id.equals(livro.getId()));
    }

    private boolean isPrecoObrigatorioNulo(Livro livro) {
        return livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO && livro.getPreco() == null;
    }
}
