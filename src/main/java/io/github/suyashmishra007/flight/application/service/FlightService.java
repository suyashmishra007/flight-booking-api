package io.github.suyashmishra007.flight.application.service;

import io.github.suyashmishra007.flight.application.model.Flight;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class FlightService {

    private final Map<String, Flight> flights = new ConcurrentHashMap<>();

    public FlightService() {
        // Sample in-memory flights
        flights.put("AB123", new Flight("AB123", 2));
        flights.put("CD456", new Flight("CD456", 5));
    }

    public Flight getFlight(String flightNumber) {
        if (flightNumber == null) return null;
        return flights.get(flightNumber.toUpperCase());
    }

    public synchronized boolean bookSeat(String flightNumber) {
        if (flightNumber == null) return false;

        Flight flight = flights.get(flightNumber.toUpperCase());
        if (flight == null) return false;

        if (flight.getBookedSeats() >= flight.getTotalCapacity()) {
            return false;
        }

        flight.incrementBookedSeats();
        return true;
    }
}