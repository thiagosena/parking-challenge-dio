package com.thiagosena.parking.domain.services;

import com.thiagosena.parking.api.ParkingAlreadyExitException;
import com.thiagosena.parking.api.ParkingNotFoundException;
import com.thiagosena.parking.api.payload.ParkingCreateDto;
import com.thiagosena.parking.api.payload.ParkingDto;
import com.thiagosena.parking.domain.ParkingRepository;
import com.thiagosena.parking.domain.entities.Parking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    private static final Logger log = LoggerFactory.getLogger(ParkingService.class);

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true)
    public List<ParkingDto> findAll() {
        log.info("List all parking");
        return parkingRepository.findAll().stream().map(ParkingDto::of).toList();
    }

    @Transactional(readOnly = true)
    public ParkingDto findById(String id) {
        log.info("Find parking by id = {}", id);
        return ParkingDto.of(findParkingById(id));
    }

    private Parking findParkingById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> {
            log.error("Parking with id: {} not founded", id);
            return new ParkingNotFoundException(id);
        });
    }

    @Transactional
    public ParkingDto create(ParkingCreateDto parkingCreateDto) {
        String uuid = getUUID();
        Parking newParking = Parking.of(parkingCreateDto);
        newParking.setId(uuid);
        newParking.setEntryDate(LocalDateTime.now());
        Parking parkingSaved = parkingRepository.save(newParking);
        log.info("Parking with id {} was created", uuid);
        return ParkingDto.of(parkingSaved);
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
        log.info("Parking with id {} was deleted", id);
    }

    @Transactional
    public ParkingDto update(String id, ParkingCreateDto parkingCreate) {

        Parking parking = findParkingById(id);

        parking.setColor(parkingCreate.color());
        parking.setState(parkingCreate.state());
        parking.setModel(parkingCreate.model());
        parking.setLicense(parkingCreate.license());
        parkingRepository.save(parking);
        log.info("Parking with id {} was updated", id);
        return ParkingDto.of(parking);
    }

    @Transactional
    public ParkingDto checkOut(String id) {
        Parking parking = findParkingById(id);
        if (parking.getExitDate() != null) {
            throw new ParkingAlreadyExitException(id);
        }
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        log.info("Parking with id {} was checking out", id);
        return ParkingDto.of(parkingRepository.save(parking));
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}