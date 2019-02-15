package com.training.myservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor @Getter
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
