package com.training.myservice.exception;

import com.training.myservice.users.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> defaultExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(createExceptionResponse(ex, request), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFOundException(UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(createExceptionResponse(ex, request), NOT_FOUND);
    }

    private ExceptionResponse createExceptionResponse(Exception ex, WebRequest request) {
        return new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    }
}
