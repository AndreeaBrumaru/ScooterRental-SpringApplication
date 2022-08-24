package com.practice.scooterrentalspringapplication.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException() {
        super("No data found.");
    }
}
