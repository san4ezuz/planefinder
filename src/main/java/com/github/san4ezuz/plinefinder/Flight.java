package com.github.san4ezuz.plinefinder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
    @Id
    private Long id;
    private String icao24;
    private String callsign;
    @JsonProperty("est_departure_airport")
    private String estDepartureAirport;
    @JsonProperty("est_arrival_airport")
    private String estArrivalAirport;
    @JsonProperty("last_seen_time")
    private Instant lastSeenTime;
    @JsonProperty("first_seen_time")
    private Instant firstSeenTime;

    public Flight(String icao24, String callsign, String estArrivalAirport, String estDepartureAirport) {

        this(null, icao24, callsign, estArrivalAirport, estDepartureAirport,
                Instant.now(), Instant.now());
    }
}
