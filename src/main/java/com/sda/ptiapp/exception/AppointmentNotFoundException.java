package com.sda.ptiapp.exception;

public class AppointmentNotFoundException extends RuntimeException{
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
