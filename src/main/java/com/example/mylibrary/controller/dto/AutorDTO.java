package com.example.mylibrary.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.example.mylibrary.model.Autor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// notblank e notnull sao validacoes do bean validation
//o notblank valida se a string nao eh nula e nao eh vazia
// not null valida se nao e null
public record AutorDTO(UUID id, @NotBlank(message = "Campo obrigagorio") String nome, @NotNull LocalDate dataNascimento, @NotBlank String nacionalidade) {

    public Autor mapearParaAutor() {
        var autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }

}
