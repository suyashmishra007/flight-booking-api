package io.github.suyashmishra007.flight.application.dto;

import jakarta.validation.constraints.NotBlank;

public class BookingRequest {

    @NotBlank(message = "flightNumber must not be blank")
    private String flightNumber;

    @NotBlank(message = "passengerName must not be blank")
    private String passengerName;

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
}
