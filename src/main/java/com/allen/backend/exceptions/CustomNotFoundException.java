package com.allen.backend.exceptions;

import java.io.Serializable;

public class CustomNotFoundException extends RuntimeException implements Serializable {

    public static final Long serialVersionUID = 1L;
    public CustomNotFoundException(String message) {
        super(message);
    }
}
