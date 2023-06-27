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

    public static final String A_NULL_VALUE_WAS_ENCOUNTERED_IN_THE_REQUEST = "A null value was encountered in the request.";
    public static final String A_NULL_VALUE_WAS_ENCOUNTERED_IN_AN_ARRAY_IN_THE_REQUEST = "A null value was encountered in an array in the request.";
    public static final String MORE_THAN_2_DIGITS_IN_NUMBER_IN_LIST = "more than 2 digits in number in list";

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
        return new ResponseEntity<>(A_NULL_VALUE_WAS_ENCOUNTERED_IN_THE_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayListContainsNullValueException.class)
    public ResponseEntity<Object> handleArrayListContainsNullValueException(ArrayListContainsNullValueException ex) {
        return new ResponseEntity<>(A_NULL_VALUE_WAS_ENCOUNTERED_IN_AN_ARRAY_IN_THE_REQUEST, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoublesMoreThanTwoDigitsException.class)
    public ResponseEntity<Object> handleDoublesMoreThanTwoDigitsException(DoublesMoreThanTwoDigitsException ex) {
        return new ResponseEntity<>(MORE_THAN_2_DIGITS_IN_NUMBER_IN_LIST, HttpStatus.BAD_REQUEST);
    }



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex.getBindingResult().hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}
