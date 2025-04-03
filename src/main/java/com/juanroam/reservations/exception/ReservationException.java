package com.juanroam.reservations.exception;

import com.juanroam.reservations.enums.APIError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
public class ReservationException extends RuntimeException {

    private HttpStatus status;

    private String description;

    private List<String> reasons;

    public ReservationException(APIError apiError) {
        this.status = apiError.getHttpStatus();
        this.description = apiError.getMessage();
    }
}
