package com.backend.management.exception;

public class OrderNotExistException extends RuntimeException{
    public OrderNotExistException(String message){
        super(message);
    }
}
