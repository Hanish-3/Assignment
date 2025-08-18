package com.example.driver_service.dao;

import com.example.driver_service.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByLicenseNumber(String licenseNumber);
    Optional<Driver> findByVehicleNumber(String vehicleNumber);
}
