package com.juanroam.reservations.conector.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class CityDTO {

    private String name;
    private String code;
    private String timeZone;
}
