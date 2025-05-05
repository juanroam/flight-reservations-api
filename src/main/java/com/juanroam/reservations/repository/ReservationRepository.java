package com.juanroam.reservations.repository;

import com.juanroam.reservations.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    List<Reservation> getReservations();

    Optional<Reservation> getReservationById(Long id);

    Reservation save(Reservation reservation);

    Reservation update(Long id, Reservation reservation);

    void delete(Long id);
}
