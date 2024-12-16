package com.example.mylibrary.exceptions;

import lombok.Getter;

public class CampoInvalidoException extends RuntimeException {
    @Getter
    private String field;

    public CampoInvalidoException(String field, String message) {
        super(message);
        this.field = field;
    }

}
