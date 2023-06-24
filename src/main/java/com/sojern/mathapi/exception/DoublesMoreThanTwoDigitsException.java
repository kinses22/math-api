package com.sojern.mathapi.exception;


public class DoublesMoreThanTwoDigitsException extends MathException{

    public DoublesMoreThanTwoDigitsException(String message) {
        super(message);
    }

    public DoublesMoreThanTwoDigitsException(String message, Throwable cause) {
        super(message, cause);
    }
}
