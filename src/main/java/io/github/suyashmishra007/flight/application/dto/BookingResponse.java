package io.github.suyashmishra007.flight.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
import java.time.Instant;

@Data
@AllArgsConstructor
public class BookingResponse {
    private UUID bookingId;
    private String flightNumber;
    private int totalCapacity;
    private int bookedSeats;
    private int remainingCapacity;
    private Instant bookedAt;
    private String passengerName;
}