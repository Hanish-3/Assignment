package com.example.vehicle_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "vehicle-topic", groupId = "vehicle-group")
    public void consumeMessage(String message) {
        System.out.println("Received message from Kafka -> " + message);
    }
}
