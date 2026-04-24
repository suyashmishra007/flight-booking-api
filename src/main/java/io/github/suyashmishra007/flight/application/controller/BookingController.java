package io.github.suyashmishra007.flight.application.controller;

import io.github.suyashmishra007.flight.application.dto.BookingRequest;
import io.github.suyashmishra007.flight.application.dto.BookingResponse;
import io.github.suyashmishra007.flight.application.model.Flight;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.suyashmishra007.flight.application.service.FlightService;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final FlightService flightService;

    public BookingController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {

        Flight flight = flightService.getFlight(request.getFlightNumber());

        // 400: flight not found
        if (flight == null) {
            return ResponseEntity.badRequest().body("Flight not found");
        }

        // Try booking
        boolean booked = flightService.bookSeat(request.getFlightNumber());

        // 409: overbooked
        if (!booked) {
            return ResponseEntity.status(409).body("Flight is fully booked");
        }

        // Success
        UUID bookingId = UUID.randomUUID();

        BookingResponse response = new BookingResponse(
                bookingId,
                flight.getFlightNumber(),
                flight.getTotalCapacity(),
                flight.getBookedSeats()
        );

        return ResponseEntity.status(201).body(response);
    }
}
