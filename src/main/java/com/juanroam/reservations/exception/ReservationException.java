package com.juanroam.reservations.exception;

public class ReservationException extends RuntimeException {

    private String description;

    public ReservationException(String message) {
        super(message);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
