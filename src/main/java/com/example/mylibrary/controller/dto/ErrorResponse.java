package com.example.mylibrary.controller.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErrorResponse(int status, String message, List<ErroDTO> errors) {
    public static ErrorResponse defaultResponse(String message) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorResponse conflict(String message) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }

}
