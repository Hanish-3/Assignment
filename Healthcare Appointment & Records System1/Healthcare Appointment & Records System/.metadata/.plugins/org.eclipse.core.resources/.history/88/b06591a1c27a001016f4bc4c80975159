    package com.example.appointment_service.dto;

    import java.time.LocalDateTime;

    public class AppointmentEvent {
        private Long appointmentId;
        private Long patientId;
        private Long doctorId;
        private LocalDateTime appointmentTime;
        private String status;
        private String eventType; // e.g., APPOINTMENT_CONFIRMED, APPOINTMENT_CANCELLED

        public AppointmentEvent() {}

        public AppointmentEvent(Long appointmentId, Long patientId, Long doctorId, LocalDateTime appointmentTime, String status, String eventType) {
            this.appointmentId = appointmentId;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.appointmentTime = appointmentTime;
            this.status = status;
            this.eventType = eventType;
        }

        // Getters and Setters
        public Long getAppointmentId() { return appointmentId; }
        public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
        public Long getPatientId() { return patientId; }
        public void setPatientId(Long patientId) { this.patientId = patientId; }
        public Long getDoctorId() { return doctorId; }
        public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
        public LocalDateTime getAppointmentTime() { return appointmentTime; }
        public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getEventType() { return eventType; }
        public void setEventType(String eventType) { this.eventType = eventType; }

        @Override
        public String toString() {
            return "AppointmentEvent{" +
                   "appointmentId=" + appointmentId +
                   ", patientId=" + patientId +
                   ", doctorId=" + doctorId +
                   ", appointmentTime=" + appointmentTime +
                   ", status='" + status + '\'' +
                   ", eventType='" + eventType + '\'' +
                   '}';
        }
    }
    