package com.juanroam.reservations.service.impl;

import com.juanroam.reservations.conector.CatalogConnector;
import com.juanroam.reservations.conector.response.CityDTO;
import com.juanroam.reservations.dto.ReservationDTO;
import com.juanroam.reservations.dto.SegmentDTO;
import com.juanroam.reservations.enums.APIError;
import com.juanroam.reservations.exception.ReservationException;
import com.juanroam.reservations.model.Reservation;
import com.juanroam.reservations.notifier.NotifierPublisher;
import com.juanroam.reservations.notifier.NotifierType;
import com.juanroam.reservations.repository.ReservationRepository;
import com.juanroam.reservations.service.ReservationService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository repository;

    private final ConversionService conversionService;

    private final CatalogConnector connector;

    private final NotifierPublisher notifierPublisher;

    public ReservationServiceImpl(ReservationRepository repository,
                                  ConversionService conversionService,
                                  CatalogConnector connector,
                                  NotifierPublisher notifierPublisher) {
        this.repository = repository;
        this.conversionService = conversionService;
        this.connector = connector;
        this.notifierPublisher = notifierPublisher;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReservationDTO> getReservations() {
        return conversionService.convert(repository.getReservations(), List.class);
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        Optional<Reservation> result = repository.getReservationById(id);
        if (result.isEmpty()) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        return conversionService.convert(result.get(), ReservationDTO.class);
    }

    @Override
    public ReservationDTO save(ReservationDTO reservation) {
        if (Objects.nonNull(reservation.getId())) {
            throw new ReservationException(APIError.RESERVATION_WITH_SAME_ID);
        }
        return persistReservation(reservation, repository::save);
    }

    @Override
    public ReservationDTO update(Long id, ReservationDTO reservation) {
        if (getReservationById(id) == null) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        return persistReservation(reservation, t -> repository.update(id, t));
    }

    private ReservationDTO persistReservation(ReservationDTO reservation,
                                              Function<Reservation, Reservation> persistFunction) {
        this.checkCity(reservation);
        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        Reservation result = persistFunction.apply(transformed);
        notifierPublisher.notify(NotifierType.EMAIL_NOTIFIER, result.getId().toString());
        return conversionService.convert(result, ReservationDTO.class);
    }

    private void checkCity(@NotNull ReservationDTO reservationDTO) {
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

    @Override
    public void delete(Long id) {
        if (getReservationById(id) == null) {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
        repository.delete(id);
    }
}
