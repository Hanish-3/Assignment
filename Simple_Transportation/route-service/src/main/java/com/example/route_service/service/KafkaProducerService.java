package com.example.route_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "route-events";

    private final KafkaTemplate<String, String> kafkaTemplate;

    // Constructor Injection
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to send a message to Kafka
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("✅ Sent message to Kafka: " + message);
    }
}
