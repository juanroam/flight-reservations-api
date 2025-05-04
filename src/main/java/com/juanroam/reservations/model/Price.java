package com.juanroam.reservations.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Price {

    private Long id;

    private BigDecimal totalPrice;

    private BigDecimal totalTax;

    private BigDecimal basePrice;

}
