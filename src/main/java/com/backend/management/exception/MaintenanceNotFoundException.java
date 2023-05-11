package com.backend.management.exception;

public class MaintenanceNotFoundException extends RuntimeException{
    public MaintenanceNotFoundException(String message){
        super(message);
    }
}
