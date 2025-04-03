package com.juanroam.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Passenger {

    private Long id;

    private String firstName;

    private String lastName;

    private String documentNumber;

    private String documentType;

    private LocalDate birthday;
}
