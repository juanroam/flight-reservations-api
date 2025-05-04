package com.juanroam.reservations.exception;

import com.juanroam.reservations.dto.ErrorDTO;
import com.juanroam.reservations.enums.APIError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class APIExceptionHandler
    extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<ErrorDTO> duplicateResource(
            ReservationException exc, WebRequest webRequest) {
        return ResponseEntity
                .status(exc.getStatus())
                .body(new ErrorDTO(exc.getDescription(), exc.getReasons()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> reasons = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            reasons.add(String.format("%s - %s", error.getField(), error.getDefaultMessage()));
        }
        return ResponseEntity
                .status(APIError.VALIDATION_ERROR.getHttpStatus())
                .body(new ErrorDTO(APIError.VALIDATION_ERROR.getMessage(), reasons));
    }
}
