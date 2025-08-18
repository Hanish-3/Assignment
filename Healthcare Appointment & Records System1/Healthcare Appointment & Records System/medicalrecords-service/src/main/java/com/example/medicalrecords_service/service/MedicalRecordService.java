    package com.example.medicalrecords_service.service;

    import com.example.medicalrecords_service.dto.AppointmentEvent;
    import com.example.medicalrecords_service.model.MedicalRecord;
    import com.example.medicalrecords_service.repository.MedicalRecordRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.kafka.annotation.KafkaListener;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.Collections;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class MedicalRecordService {
        @Autowired
        private MedicalRecordRepository medicalRecordRepository;

        public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
            return medicalRecordRepository.save(medicalRecord);
        }

        public Optional<MedicalRecord> getMedicalRecordById(String id) {
            return medicalRecordRepository.findById(id);
        }

        public List<MedicalRecord> getMedicalRecordsByPatientId(Long patientId) {
            return medicalRecordRepository.findByPatientId(patientId);
        }

        public List<MedicalRecord> getAllMedicalRecords() {
            return medicalRecordRepository.findAll();
        }

        @KafkaListener(topics = "appointment-confirmed-events", groupId = "medical-records-group", containerFactory = "appointmentEventListenerContainerFactory")
        public void handleAppointmentEvent(AppointmentEvent event) {
            System.out.println("Received Appointment Event: " + event);
            if ("APPOINTMENT_CONFIRMED".equals(event.getEventType())) {
                MedicalRecord newRecord = new MedicalRecord();
                newRecord.setPatientId(event.getPatientId());
                newRecord.setDoctorId(event.getDoctorId());
                newRecord.setVisitDate(event.getAppointmentTime());
                newRecord.setAppointmentId(event.getAppointmentId());
                newRecord.setDiagnosis("Initial visit - pending diagnosis");
                newRecord.setPrescriptions(Collections.emptyList());
                newRecord.setTestResults(Collections.emptyList());
                medicalRecordRepository.save(newRecord);
                System.out.println("Created new medical record for appointment ID: " + event.getAppointmentId());
            } else if ("APPOINTMENT_CANCELLED".equals(event.getEventType())) {
                // Handle cancellation: e.g., update status of a pending record or log
                System.out.println("Appointment cancelled, consider updating related medical records if any: " + event.getAppointmentId());
            }
        }
    }
    