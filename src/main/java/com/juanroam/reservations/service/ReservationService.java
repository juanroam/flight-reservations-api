package com.juanroam.reservations.service;

import com.juanroam.reservations.dto.ReservationDTO;
import com.juanroam.reservations.exception.ReservationException;
import com.juanroam.reservations.model.Reservation;
import com.juanroam.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepository repository;

    private ConversionService conversionService;

    @Autowired
    public ReservationService(ReservationRepository repository,
                              ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    public List<ReservationDTO> getReservations() {
        return  conversionService.convert(repository.getReservations(), List.class);
    }

    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.getReservationById(id);
        if(result.isEmpty()) {
            throw new ReservationException("Not exist");
        }
        return conversionService.convert(result.get(), ReservationDTO.class);
    }

    public ReservationDTO save(ReservationDTO reservation) {
        if(Objects.nonNull(reservation.getId())) {
            throw new ReservationException("Duplicate it");
        }

        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = repository.save(Objects.requireNonNull(transformed));
        return conversionService.convert(result, ReservationDTO.class);
    }

    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if(getReservationById(id) == null) {
            throw new ReservationException("Not exist");
        }

        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = repository.update(id, Objects.requireNonNull(transformed));
        return conversionService.convert(result, ReservationDTO.class);
    }

    public void delete(Long id) {
        if(getReservationById(id) == null) {
            throw new ReservationException("Not exist");
        }
        repository.delete(id);
    }
}
