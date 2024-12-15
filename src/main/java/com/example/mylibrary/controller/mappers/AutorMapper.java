package com.example.mylibrary.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.mylibrary.controller.dto.AutorDTO;
import com.example.mylibrary.model.Autor;

@Mapper(componentModel= "spring")
public interface AutorMapper {
    // @Mapping(source = "nome", target = "nome")
    Autor toEntity(AutorDTO dto);
    AutorDTO toDTO(Autor entity);
}
