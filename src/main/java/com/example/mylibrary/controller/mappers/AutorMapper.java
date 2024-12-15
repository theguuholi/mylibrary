package com.example.mylibrary.controller.mappers;

import org.mapstruct.Mapper;

import com.example.mylibrary.controller.dto.AutorDTO;
import com.example.mylibrary.model.Autor;

@Mapper(componentModel= "spring")
public interface AutorMapper {
    Autor toEntity(AutorDTO dto);
    AutorDTO toDTO(Autor entity);
}
