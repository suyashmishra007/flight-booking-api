package io.github.suyashmishra007.flight.application.dto;

import java.util.UUID;

public class BookingResponse {
    private UUID bookingId;
    private String flightNumber;
    private int totalCapacity;
    private int bookedSeats;

    public BookingResponse(UUID bookingId, String flightNumber, int totalCapacity, int bookedSeats) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.totalCapacity = totalCapacity;
        this.bookedSeats = bookedSeats;
    }

    public UUID getBookingId() { return bookingId; }
    public String getFlightNumber() { return flightNumber; }
    public int getTotalCapacity() { return totalCapacity; }
    public int getBookedSeats() { return bookedSeats; }
}