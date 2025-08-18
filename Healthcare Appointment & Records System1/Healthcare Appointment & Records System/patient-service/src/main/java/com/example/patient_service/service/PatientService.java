    package com.example.patient_service.service;

    import com.example.patient_service.model.Patient;
    import com.example.patient_service.repository.PatientRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class PatientService {
        @Autowired
        private PatientRepository patientRepository;

        public Patient savePatient(Patient patient) {
            return patientRepository.save(patient);
        }

        public Optional<Patient> getPatientById(Long id) {
            return patientRepository.findById(id);
        }

        public List<Patient> getAllPatients() {
            return patientRepository.findAll();
        }

        public void deletePatient(Long id) {
            patientRepository.deleteById(id);
        }
    }
    