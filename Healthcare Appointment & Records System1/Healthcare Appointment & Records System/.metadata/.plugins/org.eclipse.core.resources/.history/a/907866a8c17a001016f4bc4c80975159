    package com.example.appointment_service.service;

    import com.example.appointment_service.dto.AppointmentEvent;
    import com.example.appointment_service.dto.DoctorAvailabilityDTO;
    import com.example.appointment_service.feign.DoctorClient;
    import com.example.appointment_service.model.Appointment;
    import com.example.appointment_service.repository.AppointmentRepository;
    import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class AppointmentService {
        @Autowired
        private AppointmentRepository appointmentRepository;

        @Autowired
        private DoctorClient doctorClient;

        @Autowired
        private KafkaTemplate<Long, AppointmentEvent> kafkaTemplate;

        @Transactional
        @CircuitBreaker(name = "doctorService", fallbackMethod = "bookAppointmentFallback")
        public Appointment bookAppointment(Long patientId, Long doctorId, LocalDateTime appointmentTime) {
            // 1. Check doctor availability (simulated blocking of time slot)
            DoctorAvailabilityDTO doctor = doctorClient.getDoctorById(doctorId);
            if (doctor == null) {
                throw new RuntimeException("Doctor not found.");
            }

            // Simple availability check: check if the time is within doctor's working hours
            // and if there's no existing appointment at that exact time.
            if (appointmentTime.toLocalTime().isBefore(doctor.getAvailableFrom()) ||
                appointmentTime.toLocalTime().isAfter(doctor.getAvailableTo())) {
                throw new RuntimeException("Doctor is not available at this time.");
            }

            Optional<Appointment> existingAppointment = appointmentRepository.findByDoctorIdAndAppointmentTime(doctorId, appointmentTime);
            if (existingAppointment.isPresent()) {
                throw new RuntimeException("Time slot already booked for this doctor.");
            }

            // 2. Create appointment
            Appointment appointment = new Appointment();
            appointment.setPatientId(patientId);
            appointment.setDoctorId(doctorId);
            appointment.setAppointmentTime(appointmentTime);
            appointment.setStatus("CONFIRMED"); // Assuming immediate confirmation for simplicity

            Appointment savedAppointment = appointmentRepository.save(appointment);

            // 3. Publish "Appointment Confirmed" event
            AppointmentEvent event = new AppointmentEvent(
                    savedAppointment.getId(),
                    savedAppointment.getPatientId(),
                    savedAppointment.getDoctorId(),
                    savedAppointment.getAppointmentTime(),
                    savedAppointment.getStatus(),
                    "APPOINTMENT_CONFIRMED"
            );
            kafkaTemplate.send("appointment-confirmed-events", savedAppointment.getId(), event);

            return savedAppointment;
        }

        public Appointment bookAppointmentFallback(Long patientId, Long doctorId, LocalDateTime appointmentTime, Throwable t) {
            System.err.println("Fallback for bookAppointment: Doctor Service is unavailable. Cause: " + t.getMessage());
            // Log the error, potentially store the request for retry, or notify an admin.
            // For now, we'll return null to indicate failure.
            return null;
        }

        public Optional<Appointment> getAppointmentById(Long id) {
            return appointmentRepository.findById(id);
        }

        public List<Appointment> getAllAppointments() {
            return appointmentRepository.findAll();
        }

        @Transactional
        public Appointment rescheduleAppointment(Long appointmentId, LocalDateTime newAppointmentTime) {
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new RuntimeException("Appointment not found."));

            // Implement logic to check new time slot availability with Doctor Service
            // For simplicity, skipping detailed check here.
            appointment.setAppointmentTime(newAppointmentTime);
            appointment.setStatus("RESCHEDULED");
            Appointment updatedAppointment = appointmentRepository.save(appointment);

            // Publish "Appointment Rescheduled" event
            AppointmentEvent event = new AppointmentEvent(
                    updatedAppointment.getId(),
                    updatedAppointment.getPatientId(),
                    updatedAppointment.getDoctorId(),
                    updatedAppointment.getAppointmentTime(),
                    updatedAppointment.getStatus(),
                    "APPOINTMENT_RESCHEDULED"
            );
            kafkaTemplate.send("appointment-confirmed-events", updatedAppointment.getId(), event);

            return updatedAppointment;
        }

        @Transactional
        public void cancelAppointment(Long id) {
            Appointment appointment = appointmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appointment not found."));
            appointment.setStatus("CANCELLED");
            appointmentRepository.save(appointment);

            // Publish "Appointment Cancelled" event
            AppointmentEvent event = new AppointmentEvent(
                    appointment.getId(),
                    appointment.getPatientId(),
                    appointment.getDoctorId(),
                    appointment.getAppointmentTime(),
                    appointment.getStatus(),
                    "APPOINTMENT_CANCELLED"
            );
            kafkaTemplate.send("appointment-confirmed-events", appointment.getId(), event);
        }
    }
    