package com.example.driver_service.kafka;

import com.example.driver_service.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DriverProducer {

    private static final String DRIVER_TOPIC = "driver-events";

    @Autowired
    private KafkaTemplate<String, Driver> kafkaTemplate;

    // Send driver created event
    public void sendDriverCreated(Driver driver) {
        kafkaTemplate.send(DRIVER_TOPIC, "CREATED", driver);
        System.out.println("Sent CREATED event for Driver: " + driver.getId());
    }

    // Send driver updated event
    public void sendDriverUpdated(Driver driver) {
        kafkaTemplate.send(DRIVER_TOPIC, "UPDATED", driver);
        System.out.println("Sent UPDATED event for Driver: " + driver.getId());
    }

    // Send driver deleted event
    public void sendDriverDeleted(Driver driver) {
        kafkaTemplate.send(DRIVER_TOPIC, "DELETED", driver);
        System.out.println("Sent DELETED event for Driver: " + driver.getId());
    }
}
