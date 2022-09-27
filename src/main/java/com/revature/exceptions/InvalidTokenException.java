package com.revature.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Invalid token provided, could not parse claims");
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
