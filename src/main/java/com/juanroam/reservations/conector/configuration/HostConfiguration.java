package com.juanroam.reservations.conector.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class HostConfiguration {

    private String host;
    private int port;
    private Map<String, EndpointConfiguration> endpoints;
}
