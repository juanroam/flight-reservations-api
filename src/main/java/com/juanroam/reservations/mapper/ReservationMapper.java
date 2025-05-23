package com.juanroam.reservations.mapper;

import com.juanroam.reservations.dto.ReservationDTO;
import com.juanroam.reservations.model.Reservation;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends Converter<Reservation, ReservationDTO> {

    @Override
    ReservationDTO convert(Reservation source);
}
