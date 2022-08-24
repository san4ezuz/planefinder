DROP TABLE IF EXISTS flight;

CREATE TABLE flight
(
    id                    BIGINT auto_increment primary key,
    icao24                VARCHAR(10),
    est_arrival_airport   VARCHAR(10),
    est_departure_airport VARCHAR(10),
    callsign              VARCHAR(7),
    last_seen_time        TIMESTAMP,
    first_seen_time       TIMESTAMP
);
