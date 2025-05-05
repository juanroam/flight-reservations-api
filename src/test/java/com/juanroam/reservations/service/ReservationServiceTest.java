package com.juanroam.reservations.service;

import com.juanroam.reservations.conector.CatalogConnector;
import com.juanroam.reservations.dto.ReservationDTO;
import com.juanroam.reservations.enums.APIError;
import com.juanroam.reservations.exception.ReservationException;
import com.juanroam.reservations.model.Reservation;
import com.juanroam.reservations.notifier.NotifierPublisher;
import com.juanroam.reservations.persistence.ReservationInMemoryRepository;
import com.juanroam.reservations.repository.ReservationRepository;
import com.juanroam.reservations.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

import static com.juanroam.reservations.util.ReservationUtil.getReservation;
import static com.juanroam.reservations.util.ReservationUtil.getReservationDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository = new ReservationInMemoryRepository();

    @Mock
    ConversionService conversionService;

    @Mock
    CatalogConnector catalogConnector;

    @Mock
    NotifierPublisher notifierPublisher;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Tag("success-case")
    @Test
    void getReservationsShouldReturnInformation() {
        ReservationService reservationService = new ReservationServiceImpl(
                reservationRepository, conversionService, catalogConnector, notifierPublisher);

        Reservation reservationModel = getReservation(1L, "BOG", "ADZ");
        when(reservationRepository.getReservationById(1L))
                .thenReturn(Optional.of(reservationModel));

        ReservationDTO reservationDTO = getReservationDTO(1L, "BOG", "ADZ");
        when(conversionService.convert(reservationModel, ReservationDTO.class))
                .thenReturn(reservationDTO);

        ReservationDTO result = reservationService.getReservationById(1L);

        verify(reservationRepository, Mockito.atMostOnce()).getReservationById(1L);
        verify(conversionService, Mockito.atMostOnce()).convert(reservationModel, ReservationDTO.class);
        verify(catalogConnector, Mockito.never()).getCity(any());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1L, result.getId())
        );
    }

    @Tag("error-case")
    @Test
    void getReservationsShouldNotReturnInformation() {
        ReservationService reservationService = new ReservationServiceImpl(
                reservationRepository, conversionService, catalogConnector, notifierPublisher);

        when(reservationRepository.getReservationById(999L))
                .thenReturn(Optional.empty());

        ReservationException exception = assertThrows(ReservationException.class,
                () -> reservationService.getReservationById(999L));

        verify(reservationRepository, Mockito.atMostOnce()).getReservationById(999L);

        assertAll(
                () -> assertNotNull(exception),
                () -> assertEquals(
                        APIError.RESERVATION_NOT_FOUND.getMessage(), exception.getDescription()),
                () -> assertEquals(
                        APIError.RESERVATION_NOT_FOUND.getHttpStatus(), exception.getStatus())
        );
    }

    @Tag("success-case")
    @Test
    void deleteShouldRemoveReservation() {
        ReservationService reservationService = new ReservationServiceImpl(
                reservationRepository, conversionService, catalogConnector, notifierPublisher);

        Reservation reservationModel = getReservation(5L, "RON", "AMX");
        when(reservationRepository.getReservationById(5L)).thenReturn(Optional.of(reservationModel));

        ReservationDTO reservationDTO = getReservationDTO(5L, "RON", "AMX");
        when(conversionService.convert(reservationModel, ReservationDTO.class)).thenReturn(reservationDTO);

        doNothing().when(reservationRepository).delete(5L);

        reservationService.delete(5L);

        verify(reservationRepository, Mockito.atMostOnce()).delete(5L);
        verify(reservationRepository, Mockito.atMostOnce()).getReservationById(5L);
        verify(conversionService, Mockito.atMostOnce()).convert(reservationModel, ReservationDTO.class);
        verify(catalogConnector, Mockito.never()).getCity(any());
        verify(notifierPublisher, Mockito.never()).notify(any(), any());
    }
}