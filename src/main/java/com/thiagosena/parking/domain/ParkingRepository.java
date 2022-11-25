package com.thiagosena.parking.domain;

import com.thiagosena.parking.domain.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, String> {
}