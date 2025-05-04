package com.juanroam.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Itinerary {

    private Long id;

    private List<Segment> segment;

    private Price price;
}
