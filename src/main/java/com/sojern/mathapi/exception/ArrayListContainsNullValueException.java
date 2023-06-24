package com.sojern.mathapi.exception;


public class ArrayListContainsNullValueException extends MathException{

    public ArrayListContainsNullValueException(String message) {
        super(message);
    }

    public ArrayListContainsNullValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
