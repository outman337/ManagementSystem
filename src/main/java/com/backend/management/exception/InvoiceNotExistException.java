package com.backend.management.exception;

public class InvoiceNotExistException extends RuntimeException{
    public InvoiceNotExistException(String message) {
        super(message);
    }
}
