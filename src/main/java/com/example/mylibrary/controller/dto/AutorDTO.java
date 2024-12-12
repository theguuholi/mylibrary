package com.example.mylibrary.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.example.mylibrary.model.Autor;

public record AutorDTO(UUID id, String nome, LocalDate dataNascimento, String nacionalidade) {

    public Autor mapearParaAutor() {
        var autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }

}
