    package com.example.billing_service.model;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import java.time.LocalDateTime;

    @Entity
    public class Invoice {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long patientId;
        private Long appointmentId;
        private Double amount;
        private String status; // e.g., PENDING, PAID, CANCELLED
        private LocalDateTime invoiceDate;
        private String insuranceClaimStatus; // e.g., SUBMITTED, APPROVED, REJECTED

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getPatientId() { return patientId; }
        public void setPatientId(Long patientId) { this.patientId = patientId; }
        public Long getAppointmentId() { return appointmentId; }
        public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
        public Double getAmount() { return amount; }
        public void setAmount(Double amount) { this.amount = amount; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public LocalDateTime getInvoiceDate() { return invoiceDate; }
        public void setInvoiceDate(LocalDateTime invoiceDate) { this.invoiceDate = invoiceDate; }
        public String getInsuranceClaimStatus() { return insuranceClaimStatus; }
        public void setInsuranceClaimStatus(String insuranceClaimStatus) { this.insuranceClaimStatus = insuranceClaimStatus; }
    }
    