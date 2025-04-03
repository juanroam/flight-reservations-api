package com.juanroam.reservations.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PassengerDTO {

    private String firstName;

    private String lastName;

    private String documentNumber;

    private String documentType;

    private LocalDate birthday;
}
