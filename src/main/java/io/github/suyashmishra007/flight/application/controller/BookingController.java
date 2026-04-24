package io.github.suyashmishra007.flight.application.controller;

import io.github.suyashmishra007.flight.application.dto.BookingRequest;
import io.github.suyashmishra007.flight.application.dto.BookingResponse;
import io.github.suyashmishra007.flight.application.exception.OverbookingException;
import io.github.suyashmishra007.flight.application.exception.FlightNotFoundException;
import io.github.suyashmishra007.flight.application.model.Flight;
import io.github.suyashmishra007.flight.application.service.IdempotencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.suyashmishra007.flight.application.service.FlightService;

import java.time.Instant;
import java.util.UUID;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final FlightService flightService;
    private final IdempotencyService idempotencyService;

    public BookingController(FlightService flightService, IdempotencyService idempotencyService) {
        this.flightService = flightService;
        this.idempotencyService = idempotencyService;
    }
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request,
            @RequestHeader(value = "Idempotency-Key", required = false) String key) {

        if (key != null) {
            BookingResponse existing = idempotencyService.get(key);
            if (existing != null) {
                return ResponseEntity.ok(existing); // replay
            }
        }

        Flight flight = flightService.getFlight(request.getFlightNumber());
        if (flight == null) {
            throw new FlightNotFoundException(request.getFlightNumber());
        }

        boolean booked = flightService.bookSeat(request.getFlightNumber());
        if (!booked) {
            throw new OverbookingException(request.getFlightNumber());
        }

        UUID bookingId = UUID.randomUUID();
        BookingResponse response = new BookingResponse(
                bookingId,
                flight.getFlightNumber(),
                flight.getTotalCapacity(),
                flight.getBookedSeats(),
                flight.getTotalCapacity() - flight.getBookedSeats(),
                java.time.Instant.now(),
                request.getPassengerName()
                );

        if (key != null) {
            BookingResponse existing = idempotencyService.putIfAbsent(key, response);
            if (existing != null) {
                return ResponseEntity.ok(existing); // another thread already stored
            }
        }

        return ResponseEntity.status(201).body(response);
    }
}