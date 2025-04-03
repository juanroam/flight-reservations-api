package com.juanroam.reservations.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum APIError {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,
            "The are attributes with wrong values"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST,
            "The message not have a correct form"),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "Reservation not found"),
    RESERVATION_WITH_SAME_ID(HttpStatus.BAD_REQUEST,
            "There is a reservation with the same id");

    private final HttpStatus httpStatus;

    private final String message;
}
