package com.juanroam.reservations.repository;

import com.juanroam.reservations.model.Reservation;
import com.juanroam.reservations.persistence.ReservationInMemoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Optional;

import static com.juanroam.reservations.util.ReservationUtil.getReservation;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Check the functionality of the repository")
class ReservationRepositoryTest {

    @Nested
    class GetReservation {
        @Tag("success-case")
        @Test
        void getReservationShouldReturnInformation() {
            ReservationRepository repository = new ReservationInMemoryRepository();

            Optional<Reservation> result = repository.getReservationById(1L);

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertTrue(result.isPresent()),
                    () -> assertEquals(1L, result.get().getId())
            );
        }

        @Tag("error-case")
        @Test
        void getReservationShouldNotReturnInformation() {
            ReservationRepository repository = new ReservationInMemoryRepository();

            Optional<Reservation> result = repository.getReservationById(99L);

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertTrue(result.isEmpty())
            );
        }
    }

    @Nested
    class SaveReservation {
        @Tag("success-case")
        @Test
        void saveShouldReturnInformation() {
            ReservationRepository repository = new ReservationInMemoryRepository();

            Reservation reservation = repository.save(getReservation(null, "RON", "AXM"));

            assertAll(
                    () -> assertNotNull(reservation),
                    () -> assertEquals("RON", reservation.getItinerary().getSegment().get(0).getOrigin()),
                    () -> assertEquals("AXM", reservation.getItinerary().getSegment().get(0).getDestination())
            );
        }

        @Tag("success-case")
        @ParameterizedTest
        @CsvFileSource(resources = "/save-repository.csv", numLinesToSkip = 1)
        void saveShouldReturnInformationUsingExternalFile(String origin, String destination) {
            ReservationRepository repository = new ReservationInMemoryRepository();

            Reservation reservation = repository.save(getReservation(null, origin, destination));

            assertAll(
                    () -> assertNotNull(reservation),
                    () -> assertEquals(origin, reservation.getItinerary().getSegment().get(0).getOrigin()),
                    () -> assertEquals(destination, reservation.getItinerary().getSegment().get(0).getDestination())
            );
        }
    }
}