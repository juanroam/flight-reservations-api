package com.juanroam.reservations.conector.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndpointConfiguration {

    private String url;
    private int readTimeout;
    private int writeTimeout;
    private int connectionTimeout;
}
