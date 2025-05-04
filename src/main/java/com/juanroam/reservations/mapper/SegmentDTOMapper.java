package com.juanroam.reservations.mapper;

import com.juanroam.reservations.dto.SegmentDTO;
import com.juanroam.reservations.model.Segment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface SegmentDTOMapper extends Converter<SegmentDTO, Segment> {

    @Mapping(target = "id", ignore = true)
    @Override
    Segment convert(SegmentDTO source);
}
