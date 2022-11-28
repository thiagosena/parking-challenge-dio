package com.thiagosena.parking.api.dto;

public record ParkingCreateDto(
        String license,
        String state,
        String model,
        String color
) {
}