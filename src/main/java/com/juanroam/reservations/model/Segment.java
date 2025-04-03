package com.juanroam.reservations.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Segment {

    private Long id;

    private String origin;

    private String destination;

    private String departure;

    private String arrival;

    private String carrier;
}
