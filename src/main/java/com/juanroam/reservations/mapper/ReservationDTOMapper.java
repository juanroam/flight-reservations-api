package com.juanroam.reservations.mapper;

import com.juanroam.reservations.dto.ReservationDTO;
import com.juanroam.reservations.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring", uses = {PassengerDTOMapper.class, SegmentDTOMapper.class})
public interface ReservationDTOMapper extends Converter<ReservationDTO, Reservation> {

    @Mapping(target = "itinerary.price.id", ignore = true)
    @Mapping(target = "itinerary.id", ignore = true)
    @Override
    Reservation convert(ReservationDTO source);
}
