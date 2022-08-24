package com.practice.scooterrentalspringapplication.exception;

public class RideAlreadyPaidException extends RuntimeException {
    public RideAlreadyPaidException() {
    super("Ride has already been paid.");
    }
}
