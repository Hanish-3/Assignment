package com.example.driver_service.service;

import com.example.driver_service.dao.DriverRepository;
import com.example.driver_service.model.Driver;
import com.example.driver_service.exception.DriverNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    // Kafka producer can be autowired here later
    // @Autowired
    // private DriverProducer driverProducer;

    // Create a new driver
    public Driver createDriver(Driver driver) {
        Driver savedDriver = driverRepository.save(driver);
        // Send Kafka event (example)
        // driverProducer.sendDriverCreatedEvent(savedDriver);
        return savedDriver;
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Get driver by ID
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with id: " + id));
    }

    // Update driver
    public Driver updateDriver(Long id, Driver updatedDriver) {
        Driver existingDriver = getDriverById(id);
        existingDriver.setName(updatedDriver.getName());
        existingDriver.setLicenseNumber(updatedDriver.getLicenseNumber());
        existingDriver.setVehicleNumber(updatedDriver.getVehicleNumber());
        // Update timestamp
//        existingDriver.setUpdatedAt(java.time.LocalDateTime.now());
        Driver savedDriver = driverRepository.save(existingDriver);
        // Send Kafka event (example)
        // driverProducer.sendDriverUpdatedEvent(savedDriver);
        return savedDriver;
    }

    // Delete driver
    public void deleteDriver(Long id) {
        Driver driver = getDriverById(id);
        driverRepository.delete(driver);
        // Send Kafka event (example)
        // driverProducer.sendDriverDeletedEvent(driver);
    }
}
