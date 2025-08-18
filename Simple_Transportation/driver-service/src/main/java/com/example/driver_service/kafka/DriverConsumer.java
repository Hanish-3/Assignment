package com.example.driver_service.kafka;

import com.example.driver_service.model.Driver;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DriverConsumer {

    private static final String DRIVER_TOPIC = "driver-events";

    @KafkaListener(topics = DRIVER_TOPIC, groupId = "driver-service-group")
    public void consumeDriverEvent(Driver driver) {
        System.out.println("Received driver event: " + driver);
        // TODO: Add logic to handle events (e.g., update DB, notify other services)
    }
}
