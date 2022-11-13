package ru.andrew.device.booking.application;

public class DetailsUnavailableException extends RuntimeException {
    public DetailsUnavailableException(Exception e) {
        super(e);
    }
}
