package com.practice.scooterrentalspringapplication.exception;

public class ScooterAlreadyBrokenException extends RuntimeException{
    public ScooterAlreadyBrokenException() {
        super("Scooter is already broken and awaiting to be fixed.");
    }
}
