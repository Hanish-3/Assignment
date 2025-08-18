package com.example.booking_service.kafka;

import com.example.booking_service.model.Booking;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookingConsumer {

    private static final String BOOKING_TOPIC = "booking-events";

    @KafkaListener(topics = BOOKING_TOPIC, groupId = "booking-service-group")
    public void consumeBookingEvent(Booking booking) {
        System.out.println("Received booking event: " + booking);
        // TODO: Add logic to handle the booking event
        // e.g., notify other services, update cache, log, etc.
    }
}
