package com.revature.exceptions;


public class ResourcePersistanceException extends RuntimeException{
    public ResourcePersistanceException() {
    }


    public ResourcePersistanceException(String message) {
        super(message);
    }
}
