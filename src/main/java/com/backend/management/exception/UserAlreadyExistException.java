package com.backend.management.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        //对应的message在register service里写了
        super(message);
    }
}
