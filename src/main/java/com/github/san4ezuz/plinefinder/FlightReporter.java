package com.github.san4ezuz.plinefinder;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@AllArgsConstructor
@Configuration
public class FlightReporter {

    private final FlightService flightService;

    @Bean
    Supplier<Iterable<Flight>> reportFlight() {
        return flightService::getAllFlights;
    }
}
