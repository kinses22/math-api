package com.sojern.mathapi.exception;


public class ArrayListSizeException extends MathException{

    public ArrayListSizeException(String message) {
        super(message);
    }

    public ArrayListSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
