package com.example.mylibrary.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.mylibrary.model.GeneroLivro;

public record ResultadoPesquisaLivroDTO(String titulo, String isbn, LocalDate dataPublicacao, GeneroLivro genero,
        BigDecimal preco, AutorDTO autor) {

}
