package com.juanroam.reservations.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReservationDTO {

    private Long id;

    @Valid
    @NotEmpty(message = "You need at least one passenger")
    private List<PassengerDTO> passengers;

    @Valid
    private ItineraryDTO itinerary;
}
