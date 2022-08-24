package com.github.san4ezuz.plinefinder;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @ResponseBody
    @GetMapping("/aircraft")
    public Flux<Flight> getCurrentAircraft() throws IOException {
        return flightService.getAllFlights();
    }

    @MessageMapping("acstream")
    public Flux<Flight> getCurrentACStream() throws IOException {
        return flightService.getAllFlights().concatWith(
                Flux.interval(Duration.ofSeconds(1))
                        .flatMap(l -> flightService.getAllFlights()));
    }


}
