package com.practice.scooterrentalspringapplication.exception;

public class ScooterWorkingException extends RuntimeException{
    public ScooterWorkingException() {
        super("Scooter is in perfect condition, there is no need to repair it.");
    }
}
