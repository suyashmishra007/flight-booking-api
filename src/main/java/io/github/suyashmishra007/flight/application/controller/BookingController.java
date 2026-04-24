package io.github.suyashmishra007.flight.application.controller;

import io.github.suyashmishra007.flight.application.dto.BookingRequest;
import io.github.suyashmishra007.flight.application.dto.BookingResponse;
import io.github.suyashmishra007.flight.application.exception.OverbookingException;
import io.github.suyashmishra007.flight.application.exception.FlightNotFoundException;
import io.github.suyashmishra007.flight.application.model.Flight;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.suyashmishra007.flight.application.service.FlightService;

import java.util.UUID;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final FlightService flightService;

    public BookingController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest request) {

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
                flight.getBookedSeats()
        );

        return ResponseEntity.status(201).body(response);
    }
}