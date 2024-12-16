package com.example.mylibrary.controller.mappers;

import org.mapstruct.Mapper;

import com.example.mylibrary.controller.dto.UserDTO;
import com.example.mylibrary.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
}
