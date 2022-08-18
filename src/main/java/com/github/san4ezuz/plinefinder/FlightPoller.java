package com.github.san4ezuz.plinefinder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.Base64;

@EnableScheduling
@Component
public class FlightPoller {

    private WebClient client = WebClient.create("https://opensky-network.org/api");

    private final Instant beginTime = Instant.ofEpochSecond(1517227200);
    private final Instant endTime = beginTime.plusSeconds(3600);
    private FlightRepository flightRepository;

    @Value("${opensky.name}")
    private String name;
    @Value("${opensky.password}")
    private String password;

    public FlightPoller(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Scheduled(fixedRate = 300000)
    private void pollFlights() {
        flightRepository.deleteAll();
        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/all")
                        .queryParam("begin", beginTime.plusSeconds(3600).getEpochSecond())
                        .queryParam("end", endTime.plusSeconds(3600).getEpochSecond())
                        .build())
                .header("Authorization", getBasicAuthenticationHeader(name, password))
                .retrieve()
                .bodyToFlux(Flight.class)
                .filter(flight -> flight.getEstArrivalAirport() != null && flight.getEstDepartureAirport() != null)
                .toStream()
                .forEach(flight -> flightRepository.save(flight));

        System.out.println(flightRepository.findAll());
    }

    private static final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
