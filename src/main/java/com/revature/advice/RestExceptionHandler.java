package com.revature.advice;

import com.revature.exceptions.InvalidUserInputException;
import com.revature.exceptions.NotLoggedInException;
import com.revature.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotLoggedInException.class)
    public ResponseEntity<Object> handleNotLoggedInException(HttpServletRequest request, NotLoggedInException notLoggedInException) {

        String errorMessage = "Must be logged in to perform this action";

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException resourceNotFoundException) {

        String errorMessage = "Resource could not be found in the database";

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(InvalidUserInputException.class)
    public ResponseEntity<Object> handleInvalidUserInputException(HttpServletRequest request, InvalidUserInputException invalidUserInputException) {

        String errorMessage = "Invalid User input";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
