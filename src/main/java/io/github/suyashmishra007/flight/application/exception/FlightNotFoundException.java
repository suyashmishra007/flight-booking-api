package io.github.suyashmishra007.flight.application.exception;

public class FlightNotFoundException extends BusinessException {
    public FlightNotFoundException(String flightNumber) {
        super("Flight not found: " + flightNumber);
    }
}