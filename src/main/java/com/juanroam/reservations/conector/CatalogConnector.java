package com.juanroam.reservations.conector;


import com.juanroam.reservations.conector.configuration.EndpointConfiguration;
import com.juanroam.reservations.conector.configuration.HostConfiguration;
import com.juanroam.reservations.conector.configuration.HttpConnectorConfiguration;
import com.juanroam.reservations.conector.response.CityDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Component
public class CatalogConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogConnector.class);

    private final String HOST = "api-catalog";

    private final String ENDPOINT = "get-city";

    private HttpConnectorConfiguration configuration;

    @Autowired
    public CatalogConnector(HttpConnectorConfiguration configuration) {
        this.configuration = configuration;
    }

    @CircuitBreaker(name = "api-catalog", fallbackMethod = "fallbackGetCity")
    public CityDTO getCity(String code) {
        LOGGER.info("calling to api-catalog");
        HostConfiguration hostConfiguration = configuration.getHosts().get(HOST);
        EndpointConfiguration endpointConfiguration = hostConfiguration.getEndpoints().get(ENDPOINT);

        String baseUrl = "http://" + hostConfiguration.getHost() + ":"
                + hostConfiguration.getPort() + endpointConfiguration.getUrl();

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(endpointConfiguration.getConnectionTimeout()))
                .doOnConnected(connection -> connection
                        .addHandlerFirst(new ReadTimeoutHandler(endpointConfiguration.getReadTimeout(), TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(endpointConfiguration.getWriteTimeout(), TimeUnit.MILLISECONDS)));

        WebClient client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        return client.get()
                .uri(uriBuilder -> uriBuilder.build(code))
                .retrieve()
                .bodyToMono(CityDTO.class)
                .share()
                .block();
    }

    /**
     * Define a behaviour when a circuit breaker exception occurs.
     * @param code
     * @param cnpe
     * @return
     */
    private CityDTO fallbackGetCity(String code, CallNotPermittedException cnpe) {
        LOGGER.debug("calling to fallbackGetCity-1");

        return new CityDTO();
    }

    /**
     * Define a default behaviour when an error other than a circuit breaker occurs.
     * @param code
     * @param exc
     * @return
     */
    private CityDTO fallbackGetCity(String code, Exception exc) {
        LOGGER.debug("calling to fallbackGetCity-2");

        return new CityDTO();
    }

}
