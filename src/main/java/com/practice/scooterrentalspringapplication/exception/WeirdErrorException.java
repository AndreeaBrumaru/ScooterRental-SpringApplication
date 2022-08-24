package com.practice.scooterrentalspringapplication.exception;

public class WeirdErrorException extends RuntimeException{
    public WeirdErrorException() {
        super("I'll be honest, I have no idea why or how this happened.");
    }
}
