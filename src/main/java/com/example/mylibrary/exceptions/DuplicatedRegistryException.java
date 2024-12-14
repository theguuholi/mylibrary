package com.example.mylibrary.exceptions;

public class DuplicatedRegistryException extends RuntimeException {
    public DuplicatedRegistryException(String message) {
        super(message);
    }
    
}
