package com.github.san4ezuz.plinefinder;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FlightRepository extends ReactiveCrudRepository<Flight, Long> {
}