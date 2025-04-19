package com.juanroam.reservations.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This documents the different errors when invoking the API.
 */
@Getter
@AllArgsConstructor
public enum APIError {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,
            "There are attributes with wrong values"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST,
            "The message is not formatted correctly"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "Reservation not found"),
    RESERVATION_WITH_SAME_ID(HttpStatus.BAD_REQUEST,
            "There is a reservation with the same ID"),
    EXCEED_REQUEST_LIMIT(HttpStatus.TOO_MANY_REQUESTS,
            "You exceed the max number of request");

    /**
     * HTTP Status code.
     */
    private final HttpStatus httpStatus;

    /**
     * Text describing the error that occurred.
     */
    private final String message;
}
