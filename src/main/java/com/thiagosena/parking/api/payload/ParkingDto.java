package com.thiagosena.parking.api.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.thiagosena.parking.domain.entities.Parking;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ParkingDto(
        String id,
        String license,
        String state,
        String model,
        String color,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime entryDate,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime exitDate,
        Double bill
) {
    public static ParkingDto of(Parking parking) {
        return new ParkingDto(
                parking.getId(),
                parking.getLicense(),
                parking.getState(),
                parking.getModel(),
                parking.getColor(),
                parking.getEntryDate(),
                parking.getExitDate(),
                parking.getBill()
        );
    }
}