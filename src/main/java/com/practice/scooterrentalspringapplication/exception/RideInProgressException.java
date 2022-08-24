package com.practice.scooterrentalspringapplication.exception;

public class RideInProgressException extends RuntimeException{
    public RideInProgressException() {
        super("Ride already in progress.");
    }
}
