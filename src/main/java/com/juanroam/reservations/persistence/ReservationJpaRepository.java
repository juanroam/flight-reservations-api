package com.juanroam.reservations.persistence;

import com.juanroam.reservations.enums.APIError;
import com.juanroam.reservations.exception.ReservationException;
import com.juanroam.reservations.model.Reservation;
import com.juanroam.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql")
public class ReservationJpaRepository implements ReservationRepository {

    private final JpaRepository<Reservation, Long> jpaRepository;

    @Autowired
    public ReservationJpaRepository(JpaRepository<Reservation, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Reservation> getReservations() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return jpaRepository.save(reservation);
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {
        Optional<Reservation> aReservation = this.getReservationById(id);
        if (aReservation.isPresent()) {
            return jpaRepository.save(reservation);
        } else {
            throw new ReservationException(APIError.RESERVATION_NOT_FOUND);
        }
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
