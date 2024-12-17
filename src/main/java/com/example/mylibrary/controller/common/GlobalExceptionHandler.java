package com.example.mylibrary.controller.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.mylibrary.controller.dto.ErroDTO;
import com.example.mylibrary.controller.dto.ErrorResponse;
import com.example.mylibrary.exceptions.CampoInvalidoException;
import com.example.mylibrary.exceptions.DuplicatedRegistryException;

@RestControllerAdvice
// vai capturar todas as excecoes lancadas pelos controllers e retornar uma
// resposta adequada REST
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(fe -> new ErroDTO(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validacao", errors);
    }

    @ExceptionHandler(DuplicatedRegistryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicatedRegistryException(DuplicatedRegistryException e) {
        return ErrorResponse.conflict(e.getMessage());
    }

    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleCampoInvalidoException(CampoInvalidoException e) {
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validacao", List.of(
                new ErroDTO(e.getField(), e.getMessage())));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException e) {
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Acesso negado", List.of());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericErrors(RuntimeException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unnexpected error!", List.of());
    }

}
