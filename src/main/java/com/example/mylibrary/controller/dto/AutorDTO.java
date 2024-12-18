package com.example.mylibrary.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.example.mylibrary.model.Autor;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

// notblank e notnull sao validacoes do bean validation
//o notblank valida se a string nao eh nula e nao eh vazia
// not null valida se nao e null
@Schema(name = "Autor")
public record AutorDTO(UUID id,
        @NotBlank(message = "Campo obrigagorio") @Size(max = 100, min = 2) String nome,
        @NotNull @Past LocalDate dataNascimento,
        @NotBlank @Size(min = 2, max = 50) String nacionalidade) {

    public Autor mapearParaAutor() {
        var autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }

}
