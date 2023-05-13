package com.backend.management.exception;

public class MaintenanceAlreadyExistException extends RuntimeException{
    public MaintenanceAlreadyExistException(String message) {
        //对应的message在register service里写了
        super(message);
    }
}
