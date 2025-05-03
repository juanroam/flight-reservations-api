package com.juanroam.reservations.service;

import com.juanroam.reservations.conector.CatalogConnector;
import com.juanroam.reservations.conector.response.CityDTO;
import com.juanroam.reservations.dto.ReservationDTO;
import com.juanroam.reservations.dto.SegmentDTO;
import com.juanroam.reservations.enums.APIError;
import com.juanroam.reservations.exception.ReservationException;
import com.juanroam.reservations.model.Reservation;
import com.juanroam.reservations.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationRepository repository;

    private final ConversionService conversionService;

    private final CatalogConnector connector;

    @Autowired
    public ReservationService(ReservationRepository repository,
                              ConversionService conversionService,
                              CatalogConnector connector) {
        this.repository = repository;
        this.conversionService = conversionService;
        this.connector = connector;
    }

    public List<ReservationDTO> getReservations() {
        return  conversionService.convert(repository.getReservations(), List.class);
    }

    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.getReservationById(id);
        if(result.isEmpty()) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        return conversionService.convert(result.get(), ReservationDTO.class);
    }

    public ReservationDTO save(ReservationDTO reservation) {
        if(Objects.nonNull(reservation.getId())) {
            throw new ReservationException(APIError.RESERVATION_WITH_SAME_ID);
        }
        this.checkCity(reservation);
        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = repository.save(Objects.requireNonNull(transformed));
        return conversionService.convert(result, ReservationDTO.class);
    }

    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if(getReservationById(id) == null) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        this.checkCity(reservation);
        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = repository.update(id, Objects.requireNonNull(transformed));
        return conversionService.convert(result, ReservationDTO.class);
    }

    public void delete(Long id) {
        if(getReservationById(id) == null) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        repository.delete(id);
    }

    private void checkCity(ReservationDTO reservationDTO) {
        for (SegmentDTO segmentDTO : reservationDTO.getItinerary().getSegment()) {
            CityDTO origin = connector.getCity(segmentDTO.getOrigin());
            CityDTO destination = connector.getCity(segmentDTO.getDestination());

            if (origin == null || destination == null) {
                throw new ReservationException(APIError.VALIDATION_ERROR);
            } else {
                LOGGER.info("from={} to={}", origin.getName(), destination.getName());
            }
        }
    }
}
