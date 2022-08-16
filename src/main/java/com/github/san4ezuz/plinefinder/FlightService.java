package com.github.san4ezuz.plinefinder;

import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Iterable<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
