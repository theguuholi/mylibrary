package com.example.mylibrary.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.mylibrary.controller.dto.CadastroLivroDTO;
import com.example.mylibrary.controller.dto.ResultadoPesquisaLivroDTO;
import com.example.mylibrary.model.Livro;
import com.example.mylibrary.repository.AutorRepository;

@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {
    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.autorId()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);
}
