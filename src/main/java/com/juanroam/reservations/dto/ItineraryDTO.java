package com.juanroam.reservations.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ItineraryDTO {

    private List<SegmentDTO> segment;

    private PriceDTO price;
}
