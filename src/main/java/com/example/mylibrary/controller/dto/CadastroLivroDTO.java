package com.example.mylibrary.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;

import com.example.mylibrary.model.GeneroLivro;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CadastroLivroDTO(@NotBlank String titulo, @NotBlank @ISBN String isbn, @NonNull @Past LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco, @NotNull UUID autorId) {

}
