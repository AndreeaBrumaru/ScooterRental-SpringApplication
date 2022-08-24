package com.practice.scooterrentalspringapplication.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BatteryTooLowException.class)
    public ResponseEntity<String> handleBatteryTooLowException(
            BatteryTooLowException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeirdErrorException.class)
    public ResponseEntity<String> handleWeirdErrorException(
            WeirdErrorException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(ScooterWorkingException.class)
    public ResponseEntity<String> handleScooterWorkingException(
            ScooterWorkingException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RideAlreadyPaidException.class)
    public ResponseEntity<String> handleRideAlreadyPaidException(
            RideAlreadyPaidException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ScooterAlreadyBrokenException.class)
    public ResponseEntity<String> handleScooterAlreadyBrokenException(
            ScooterAlreadyBrokenException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScooterNotFoundException.class)
    public ResponseEntity<String> handleScooterNotFoundException(
            ScooterNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<String> handleNoDataFoundException(
            NoDataFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoRideToProcessException.class)
    public ResponseEntity<String> handleNoRideToProcessException(
            NoRideToProcessException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RideInProgressException.class)
    public ResponseEntity<String> handleRideInProgressException(
            RideInProgressException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
}
