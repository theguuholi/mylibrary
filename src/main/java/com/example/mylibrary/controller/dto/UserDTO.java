package com.example.mylibrary.controller.dto;

import java.util.List;

import jakarta.validation.constraints.Email;

public record UserDTO(String login, String password, List<String> roles, @Email String email) {

}
