package com.juanroam.reservations.dto;

import com.juanroam.reservations.validator.AirportFormatConstraint;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SegmentDTO {

    @AirportFormatConstraint
    private String origin;

    @AirportFormatConstraint
    private String destination;

    private String departure;

    private String arrival;

    private String carrier;
}
