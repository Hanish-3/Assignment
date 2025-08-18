    package com.example.appointment_service.controller;

    import com.example.appointment_service.model.Appointment;
    import com.example.appointment_service.service.AppointmentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.time.LocalDateTime;
    import java.util.List;

    @RestController
    @RequestMapping("/api/appointments")
    public class AppointmentController {
        @Autowired
        private AppointmentService appointmentService;

        @PostMapping
        public ResponseEntity<Appointment> bookAppointment(
                @RequestParam Long patientId,
                @RequestParam Long doctorId,
                @RequestParam String appointmentTime) { // Format: yyyy-MM-ddTHH:mm:ss
            try {
                LocalDateTime time = LocalDateTime.parse(appointmentTime);
                Appointment newAppointment = appointmentService.bookAppointment(patientId, doctorId, time);
                if (newAppointment != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
                } else {
                    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); // Doctor service unavailable
                }
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Or a more specific error DTO
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
            return appointmentService.getAppointmentById(id)
                    .map(appointment -> new ResponseEntity<>(appointment, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping
        public List<Appointment> getAllAppointments() {
            return appointmentService.getAllAppointments();
        }

        @PutMapping("/{id}/reschedule")
        public ResponseEntity<Appointment> rescheduleAppointment(
                @PathVariable Long id,
                @RequestParam String newAppointmentTime) {
            try {
                LocalDateTime newTime = LocalDateTime.parse(newAppointmentTime);
                Appointment updatedAppointment = appointmentService.rescheduleAppointment(id, newTime);
                return ResponseEntity.ok(updatedAppointment);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
            try {
                appointmentService.cancelAppointment(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
    