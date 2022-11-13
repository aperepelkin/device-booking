package ru.andrew.device.booking.domain;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
