package com.github.san4ezuz.plinefinder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @ResponseBody
    @GetMapping("/flights")
    public Iterable<Flight> getAllFlights()  {
        return flightService.getAllFlights();
    }

}
