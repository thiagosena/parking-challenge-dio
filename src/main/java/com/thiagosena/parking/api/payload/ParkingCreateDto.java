package com.thiagosena.parking.api.payload;

public record ParkingCreateDto(
        String license,
        String state,
        String model,
        String color
) {
}