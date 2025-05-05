package com.juanroam.reservations.service;

import com.juanroam.reservations.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getReservations();

    ReservationDTO getReservationById(Long id);

    ReservationDTO save(ReservationDTO reservation);

    ReservationDTO update(Long id, ReservationDTO reservation);

    void delete(Long id);
}
