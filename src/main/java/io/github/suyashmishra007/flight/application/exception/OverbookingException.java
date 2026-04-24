package io.github.suyashmishra007.flight.application.exception;

public class OverbookingException extends BusinessException {
    public OverbookingException(String flightNumber) {
        super("Flight is fully booked: " + flightNumber);
    }
}
