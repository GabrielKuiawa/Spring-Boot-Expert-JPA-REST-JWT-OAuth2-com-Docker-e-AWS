package com.github.GabrielKuiawa.libraryapi.exceptions;

public class OperationNotPermittedException extends RuntimeException {
    public OperationNotPermittedException(String message) {
        super(message);
    }
}
