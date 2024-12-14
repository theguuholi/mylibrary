package com.example.mylibrary.validator;

import org.springframework.stereotype.Component;

import com.example.mylibrary.exceptions.DuplicatedRegistryException;
import com.example.mylibrary.model.Autor;
import com.example.mylibrary.repository.AutorRepository;

@Component
public class AutorValidator {
    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validate(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw new DuplicatedRegistryException("Autor ja cadastrado");
        }
    }

    private Boolean existeAutorCadastrado(Autor autor) {
        Autor result = repository.findByNomeAndDataNascimentoAndNacionalidade(autor.getNome(),
                autor.getDataNascimento(), autor.getNacionalidade());

        if (autor.getId() == null) {
            return result != null;
        }

        return autor.getId().equals(result.getId()) && result != null;
    }
}
