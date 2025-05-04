package com.juanroam.reservations.conector.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "http-connector")
public class HttpConnectorConfiguration {

    private Map<String, HostConfiguration> hosts;
}
