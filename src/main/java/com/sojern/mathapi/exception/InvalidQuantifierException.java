package com.sojern.mathapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class InvalidQuantifierException extends MathException{

    public InvalidQuantifierException(String message) {
        super(message);
    }
}
