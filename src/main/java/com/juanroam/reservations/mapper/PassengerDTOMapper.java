package com.juanroam.reservations.mapper;

import com.juanroam.reservations.dto.PassengerDTO;
import com.juanroam.reservations.model.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface PassengerDTOMapper extends Converter<PassengerDTO, Passenger> {

    @Mapping(target = "id", ignore = true)
    @Override
    Passenger convert(PassengerDTO source);
}
