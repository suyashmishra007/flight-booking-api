package io.github.suyashmishra007.flight.application.model;

import java.util.UUID;

public class Booking {
    private UUID bookingId;
    private String flightNumber;
    private String passengerName;

    public Booking(UUID bookingId, String flightNumber, String passengerName) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
    }

    public UUID getBookingId() { return bookingId; }
    public String getFlightNumber() { return flightNumber; }
    public String getPassengerName() { return passengerName; }
}