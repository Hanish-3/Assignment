    package com.example.appointment_service.dto;

    import java.time.LocalTime;

    public class DoctorAvailabilityDTO {
        private Long id;
        private String name;
        private String specialization;
        private LocalTime availableFrom;
        private LocalTime availableTo;

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getSpecialization() { return specialization; }
        public void setSpecialization(String specialization) { this.specialization = specialization; }
        public LocalTime getAvailableFrom() { return availableFrom; }
        public void setAvailableFrom(LocalTime availableFrom) { this.availableFrom = availableFrom; }
        public LocalTime getAvailableTo() { return availableTo; }
        public void setAvailableTo(LocalTime availableTo) { this.availableTo = availableTo; }
    }
    