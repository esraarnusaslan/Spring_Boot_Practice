package com.tpe.exception;

import javax.validation.constraints.Email;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
