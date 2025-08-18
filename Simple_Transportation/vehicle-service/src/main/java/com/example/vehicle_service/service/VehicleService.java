package com.example.vehicle_service.service;

import com.example.vehicle_service.model.Vehicle;
import com.example.vehicle_service.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /** Add a new vehicle */
    public Vehicle addVehicle(Vehicle vehicle) {
        if (vehicleRepository.findByVehicleNumber(vehicle.getVehicleNumber()) != null) {
            throw new RuntimeException("Vehicle with number " + vehicle.getVehicleNumber() + " already exists");
        }
        return vehicleRepository.save(vehicle);
    }


    /** Get all vehicles */
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    /** Get vehicle by ID */
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    /** Update an existing vehicle */
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id)
                .map(existing -> {
                    existing.setVehicleNumber(updatedVehicle.getVehicleNumber());
                    existing.setType(updatedVehicle.getType());
                    existing.setCapacity(updatedVehicle.getCapacity());
                    return vehicleRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
    }

    /** Delete vehicle by ID */
    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }
}
