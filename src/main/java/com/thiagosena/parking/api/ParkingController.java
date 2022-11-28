package com.thiagosena.parking.api;

import com.thiagosena.parking.api.dto.ParkingCreateDto;
import com.thiagosena.parking.api.dto.ParkingDto;
import com.thiagosena.parking.domain.services.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDto>> findAll() {
        return ResponseEntity.ok(parkingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(parkingService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ParkingDto> create(@RequestBody ParkingCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDto> update(@PathVariable String id, @RequestBody ParkingCreateDto parkingCreateDto) {
        return ResponseEntity.ok(parkingService.update(id, parkingCreateDto));
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<ParkingDto> checkOut(@PathVariable String id) {
        return ResponseEntity.ok(parkingService.checkOut(id));
    }

}