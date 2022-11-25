package com.thiagosena.parking.service;

import com.thiagosena.parking.domain.entities.Parking;
import com.thiagosena.parking.domain.services.ParkingCheckOut;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingCheckOutTest {

    @Test
    void getBillOnHourTest() {
        LocalDateTime entry = LocalDateTime.of(2022, 11, 10, 10, 0, 0);
        LocalDateTime exit = LocalDateTime.of(2022, 11, 10, 11, 0, 0);
        Parking parking = new Parking();
        parking.setEntryDate(entry);
        parking.setExitDate(exit);
        Double bill = ParkingCheckOut.getBill(parking);
        assertEquals(5, bill);

    }

    @Test
    void getBillTwentyFourHourTest() {
        LocalDateTime entry = LocalDateTime.of(2022, 11, 10, 10, 0, 0);
        LocalDateTime exit = LocalDateTime.of(2022, 11, 11, 10, 0, 0);
        Parking parking = new Parking();
        parking.setEntryDate(entry);
        parking.setExitDate(exit);
        Double bill = ParkingCheckOut.getBill(parking);
        assertEquals(53, bill);

    }

    @Test
    void getBillDayValueTest() {
        LocalDateTime entry = LocalDateTime.of(2022, 11, 10, 10, 0, 0);
        LocalDateTime exit = LocalDateTime.of(2022, 11, 15, 12, 0, 0);
        Parking parking = new Parking();
        parking.setEntryDate(entry);
        parking.setExitDate(exit);
        Double bill = ParkingCheckOut.getBill(parking);
        assertEquals(100, bill);

    }
}