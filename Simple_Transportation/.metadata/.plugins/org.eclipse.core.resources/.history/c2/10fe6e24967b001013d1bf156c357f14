package com.example.booking_service.service;

import com.example.booking_service.exception.BookingNotFoundException;
import com.example.booking_service.kafka.BookingProducer;
import com.example.booking_service.model.Booking;
import com.example.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingProducer bookingProducer;

    // Create new booking
    public Booking createBooking(Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        bookingProducer.sendBookingCreated(savedBooking);
        return savedBooking;
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + id));
    }

    // Update booking
    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = getBookingById(id);
        booking.setCustomerName(bookingDetails.getCustomerName());
        booking.setMovieName(bookingDetails.getMovieName());
        booking.setTheaterName(bookingDetails.getTheaterName());
        booking.setSeatNumber(bookingDetails.getSeatNumber());

        Booking updatedBooking = bookingRepository.save(booking);
        bookingProducer.sendBookingUpdated(updatedBooking);
        return updatedBooking;
    }

    // Delete booking
    public void deleteBooking(Long id) {
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
        bookingProducer.sendBookingDeleted(booking);
    }
}
