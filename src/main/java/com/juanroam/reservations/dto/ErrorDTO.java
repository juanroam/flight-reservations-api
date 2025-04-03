package com.juanroam.reservations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorDTO {

    private String description;
    private List<String> reasons;
}
