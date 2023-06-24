package com.sojern.mathapi.handler;

import com.sojern.mathapi.exception.ArrayListContainsNullValueException;
import com.sojern.mathapi.exception.ArrayListSizeException;
import com.sojern.mathapi.exception.DoublesMoreThanTwoDigitsException;
import com.sojern.mathapi.exception.InvalidQuantifierException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MathExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidQuantifierException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            InvalidQuantifierException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayListSizeException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            ArrayListSizeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        String errorMessage = "A null value was encountered in the request.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayListContainsNullValueException.class)
    public ResponseEntity<Object> handleArrayListContainsNullValueException(ArrayListContainsNullValueException ex) {
        String errorMessage = "A null value was encountered in an array in the request.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoublesMoreThanTwoDigitsException.class)
    public ResponseEntity<Object> handleDoublesMoreThanTwoDigitsException(DoublesMoreThanTwoDigitsException ex) {
        String errorMessage = "more than 2 digits in number in list";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
