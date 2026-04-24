package io.github.suyashmishra007.flight.application.model;
import lombok.Data;

@Data
public class Flight {
    private String flightNumber;
    private int totalCapacity;
    private int bookedSeats;

    public Flight(String flightNumber, int totalCapacity) {
        this.flightNumber = flightNumber;
        this.totalCapacity = totalCapacity;
        this.bookedSeats = 0;
    }
    public void incrementBookedSeats() {
        this.bookedSeats++;
    }
}
