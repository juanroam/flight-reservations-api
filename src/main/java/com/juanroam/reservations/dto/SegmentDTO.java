package com.juanroam.reservations.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SegmentDTO {

    private String origin;

    private String destination;

    private String departure;

    private String arrival;

    private String carrier;
}
