package com.juanroam.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Reservation {

    private Long id;

    private List<Passenger> passengers;

    private Itinerary itinerary;
}
