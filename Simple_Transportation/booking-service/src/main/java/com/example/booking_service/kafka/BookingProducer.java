package com.example.booking_service.kafka;

import com.example.booking_service.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    private static final String BOOKING_TOPIC = "booking-events";

    @Autowired
    private KafkaTemplate<String, Booking> kafkaTemplate;

    // Send booking created event
    public void sendBookingCreated(Booking booking) {
        kafkaTemplate.send(BOOKING_TOPIC, "CREATED", booking);
        System.out.println("Sent CREATED event for Booking ID: " + booking.getId());
    }

    // Send booking updated event
    public void sendBookingUpdated(Booking booking) {
        kafkaTemplate.send(BOOKING_TOPIC, "UPDATED", booking);
        System.out.println("Sent UPDATED event for Booking ID: " + booking.getId());
    }

    // Send booking deleted event
    public void sendBookingDeleted(Booking booking) {
        kafkaTemplate.send(BOOKING_TOPIC, "DELETED", booking);
        System.out.println("Sent DELETED event for Booking ID: " + booking.getId());
    }
}