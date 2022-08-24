package com.practice.scooterrentalspringapplication.exception;

public class ScooterNotFoundException extends RuntimeException{
    public ScooterNotFoundException() {
        super("Scooter not found.");
    }
}
