package com.example.vehicle_service.controller;

import com.example.vehicle_service.model.Vehicle;
import com.example.vehicle_service.service.VehicleService;
import com.example.vehicle_service.kafka.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final KafkaProducerService kafkaProducerService;

    public VehicleController(VehicleService vehicleService, KafkaProducerService kafkaProducerService) {
        this.vehicleService = vehicleService;
        this.kafkaProducerService = kafkaProducerService;
    }

    /** Create a new vehicle */
    @PostMapping
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle savedVehicle = vehicleService.addVehicle(vehicle);

            // Send Kafka event
            kafkaProducerService.sendMessage("vehicle-topic",
                    "New vehicle added: " + savedVehicle.getVehicleNumber());

            return ResponseEntity.ok(savedVehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /** Get all vehicles */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    /** Get a vehicle by ID */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Update a vehicle */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        try {
            Vehicle updated = vehicleService.updateVehicle(id, vehicle);

            // Send Kafka event
            kafkaProducerService.sendMessage("vehicle-topic",
                    "Vehicle updated: " + updated.getVehicleNumber());

            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /** Delete a vehicle */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);

        // Send Kafka event
        kafkaProducerService.sendMessage("vehicle-topic",
                "Vehicle deleted with ID: " + id);

        return ResponseEntity.noContent().build();
    }
}
