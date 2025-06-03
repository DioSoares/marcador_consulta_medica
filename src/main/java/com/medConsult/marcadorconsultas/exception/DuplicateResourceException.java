package com.medConsult.marcadorconsultas.exception;

public class DuplicateResourceException extends ConflictException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
