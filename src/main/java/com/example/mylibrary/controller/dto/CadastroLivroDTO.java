package com.example.mylibrary.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.example.mylibrary.model.GeneroLivro;

public record CadastroLivroDTO(String titulo, String isbn, LocalDate dataPublicacao, GeneroLivro genero,
        BigDecimal preco, UUID autorId) {

}
