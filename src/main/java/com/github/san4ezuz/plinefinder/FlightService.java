package com.github.san4ezuz.plinefinder;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flux<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
