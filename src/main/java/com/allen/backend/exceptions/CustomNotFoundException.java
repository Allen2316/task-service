package com.allen.backend.exceptions;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException(String message) {
        super(message);
    }
}
