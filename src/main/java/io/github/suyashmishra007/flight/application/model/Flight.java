package io.github.suyashmishra007.flight.application.model;

public class Flight {
    private String flightNumber;
    private int totalCapacity;
    private int bookedSeats;

    public Flight(String flightNumber, int totalCapacity) {
        this.flightNumber = flightNumber;
        this.totalCapacity = totalCapacity;
        this.bookedSeats = 0;
    }

    public String getFlightNumber() { return flightNumber; }
    public int getTotalCapacity() { return totalCapacity; }
    public int getBookedSeats() { return bookedSeats; }

    public void incrementBookedSeats() {
        this.bookedSeats++;
    }
}
