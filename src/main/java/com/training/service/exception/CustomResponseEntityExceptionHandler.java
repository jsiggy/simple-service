package com.training.service.exception;

import com.training.service.users.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> defaultExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(createExceptionResponse(request.getDescription(false), ex), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFOundException(UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(createExceptionResponse(request.getDescription(false), ex), NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ExceptionResponse exceptionResponse = createExceptionResponse("Validation Failed", ex);
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    private ExceptionResponse createExceptionResponse(String description, Exception ex) {
        return new ExceptionResponse(new Date(), description, ex.getMessage());
    }
}
