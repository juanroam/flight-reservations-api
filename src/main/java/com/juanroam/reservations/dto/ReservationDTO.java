package com.juanroam.reservations.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReservationDTO {

    private Long id;

    private List<PassengerDTO> passengers;

    private ItineraryDTO itinerary;
}
