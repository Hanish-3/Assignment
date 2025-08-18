    package com.example.medicalrecords_service.model;

    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;
    import java.time.LocalDateTime;
    import java.util.List;

    @Document
    public class MedicalRecord {
        @Id
        private String id;
        private Long patientId;
        private Long doctorId;
        private LocalDateTime visitDate;
        private String diagnosis;
        private List<String> prescriptions;
        private List<String> testResults;
        private Long appointmentId; // Link to the appointment

        // Getters and Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public Long getPatientId() { return patientId; }
        public void setPatientId(Long patientId) { this.patientId = patientId; }
        public Long getDoctorId() { return doctorId; }
        public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
        public LocalDateTime getVisitDate() { return visitDate; }
        public void setVisitDate(LocalDateTime visitDate) { this.visitDate = visitDate; }
        public String getDiagnosis() { return diagnosis; }
        public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
        public List<String> getPrescriptions() { return prescriptions; }
        public void setPrescriptions(List<String> prescriptions) { this.prescriptions = prescriptions; }
        public List<String> getTestResults() { return testResults; }
        public void setTestResults(List<String> testResults) { this.testResults = testResults; }
        public Long getAppointmentId() { return appointmentId; }
        public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    }
    