package com.juanroam.reservations.persistence;

import com.juanroam.reservations.model.Reservation;
import com.juanroam.reservations.model.*;
import com.juanroam.reservations.repository.ReservationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Profile("memory")
public class ReservationInMemoryRepository implements ReservationRepository {

    static List<Reservation> reservations = new ArrayList<>();

    static {
        Passenger passenger = new Passenger();
        passenger.setFirstName("JUAN");
        passenger.setLastName("RODRIGUEZ");
        passenger.setId(1L);
        passenger.setDocumentType("CC");
        passenger.setDocumentNumber("1555333444");
        passenger.setBirthday(LocalDate.of(1991,5,21));

        Price price = new Price();
        price.setBasePrice(BigDecimal.ONE);
        price.setTotalTax(BigDecimal.ZERO);
        price.setTotalPrice(BigDecimal.ONE);

        Segment segment = new Segment();
        segment.setArrival("2025-05-26");
        segment.setDeparture("2025-05-12");
        segment.setOrigin("BOG");
        segment.setDestination("ADZ");
        segment.setCarrier("AA");
        segment.setId(1L);

        Itinerary itinerary = new Itinerary();
        itinerary.setId(1L);
        itinerary.setPrice(price);
        itinerary.setSegment(List.of(segment));

        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPassengers(List.of(passenger));
        reservation.setItinerary(itinerary);

        reservations.add(reservation);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        List<Reservation> result = reservations.stream()
                .filter(reservation -> Objects.equals(reservation.getId(), id))
                .toList();

        Reservation reservation = !result.isEmpty() ? result.get(0): null;
        return Optional.ofNullable(reservation);
    }

    @Override
    public Reservation save(Reservation reservation) {
        reservation.setId((long) (reservations.size() + 1));
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {
        List<Reservation> result = reservations.stream()
                .filter(reser -> reser.getId().equals(id))
                .toList();
        result.get(0).setId(reservation.getId());
        result.get(0).setItinerary(reservation.getItinerary());
        result.get(0).setPassengers(reservation.getPassengers());

        return result.get(0);
    }

    @Override
    public void delete(Long id) {
        List<Reservation> result = reservations.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .toList();

        reservations.remove(result.get(0));
    }
}
