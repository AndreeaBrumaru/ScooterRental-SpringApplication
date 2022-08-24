package com.practice.scooterrentalspringapplication.exception;

public class BatteryTooLowException extends RuntimeException{
    public BatteryTooLowException() {
        super("Battery to low to start ride.");
    }
}
