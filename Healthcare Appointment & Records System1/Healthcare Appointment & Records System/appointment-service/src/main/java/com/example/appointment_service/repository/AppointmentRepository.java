    package com.example.appointment_service.repository;

    import com.example.appointment_service.model.Appointment;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.time.LocalDateTime;
    import java.util.Optional;

    @Repository
    public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
        Optional<Appointment> findByDoctorIdAndAppointmentTime(Long doctorId, LocalDateTime appointmentTime);
    }
    