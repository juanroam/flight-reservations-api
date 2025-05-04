package com.juanroam.reservations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PassengerDTO {

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    private String documentNumber;

    private String documentType;

    @Past(message = "birthday need to be a date in the past")
    private LocalDate birthday;
}
