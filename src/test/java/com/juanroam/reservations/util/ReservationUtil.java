package com.juanroam.reservations.util;

import com.juanroam.reservations.dto.*;
import com.juanroam.reservations.model.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReservationUtil {

    public static ReservationDTO getReservationDTO(Long id, String origin, String destination) {
        PassengerDTO passenger = new PassengerDTO();
        passenger.setFirstName("Carlos");
        passenger.setLastName("Rodriguez");
        passenger.setDocumentType("CC");
        passenger.setDocumentNumber("12345678");
        passenger.setBirthday(LocalDate.of(1995, 1, 1));

        ItineraryDTO itinerary = getItineraryDTO(origin, destination);

        ReservationDTO reservation = new ReservationDTO();
        reservation.setId(id);
        reservation.setPassengers(List.of(passenger));
        reservation.setItinerary(itinerary);

        return reservation;
    }

    @NotNull
    private static ItineraryDTO getItineraryDTO(String origin, String destination) {
        PriceDTO price = new PriceDTO();
        price.setBasePrice(BigDecimal.ONE);
        price.setTotalTax(BigDecimal.ZERO);
        price.setTotalPrice(BigDecimal.ONE);

        SegmentDTO segment = new SegmentDTO();
        segment.setArrival("2025-06-20");
        segment.setDeparture("2025-05-31");
        segment.setOrigin(origin);
        segment.setDestination(destination);
        segment.setCarrier("AA");

        ItineraryDTO itinerary = new ItineraryDTO();
        itinerary.setPrice(price);
        itinerary.setSegment(List.of(segment));
        return itinerary;
    }

    public static Reservation getReservation(Long id, String origin, String destination) {
        Passenger passenger = new Passenger();
        passenger.setFirstName("JUAN");
        passenger.setLastName("RODRIGUEZ");
        passenger.setId(1L);
        passenger.setDocumentType("CC");
        passenger.setDocumentNumber("1555333444");
        passenger.setBirthday(LocalDate.of(1991,5,21));

        Itinerary itinerary = getItinerary(origin, destination);

        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setPassengers(List.of(passenger));
        reservation.setItinerary(itinerary);

        return reservation;
    }

    @NotNull
    private static Itinerary getItinerary(String origin, String destination) {
        Price price = new Price();
        price.setBasePrice(BigDecimal.ONE);
        price.setTotalTax(BigDecimal.ZERO);
        price.setTotalPrice(BigDecimal.ONE);

        Segment segment = new Segment();
        segment.setArrival("2025-01-01");
        segment.setDeparture("2024-12-31");
        segment.setOrigin(origin);
        segment.setDestination(destination);
        segment.setCarrier("AA");
        segment.setId(1L);

        Itinerary itinerary = new Itinerary();
        itinerary.setId(1L);
        itinerary.setPrice(price);
        itinerary.setSegment(List.of(segment));
        return itinerary;
    }
}
