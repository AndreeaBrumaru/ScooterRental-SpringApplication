package com.practice.scooterrentalspringapplication.exception;

public class NoRideToProcessException extends RuntimeException{
    public NoRideToProcessException() {
        super("No ride to process");
    }
}
