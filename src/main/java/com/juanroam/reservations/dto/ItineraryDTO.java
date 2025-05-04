package com.juanroam.reservations.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ItineraryDTO {

    @Valid
    private List<SegmentDTO> segment;

    private PriceDTO price;
}
