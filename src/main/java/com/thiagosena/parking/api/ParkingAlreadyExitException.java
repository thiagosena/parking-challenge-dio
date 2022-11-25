package com.thiagosena.parking.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ParkingAlreadyExitException extends RuntimeException {

    public ParkingAlreadyExitException(String id) {
        super("Parking already exited with id: " + id);
    }
}