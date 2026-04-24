package io.github.suyashmishra007.flight.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookingRequest {

    @NotBlank(message = "flightNumber must not be blank")
    private String flightNumber;

    @NotBlank(message = "passengerName must not be blank")
    private String passengerName;
}
