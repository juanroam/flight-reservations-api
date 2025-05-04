package com.juanroam.reservations.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PriceDTO {

    private BigDecimal totalPrice;

    private BigDecimal totalTax;

    private BigDecimal basePrice;
}
