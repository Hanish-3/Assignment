    package com.example.patient_service.controller;

    import com.example.patient_service.model.Patient;
    import com.example.patient_service.service.PatientService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/patients")
    public class PatientController {
        @Autowired
        private PatientService patientService;

        @PostMapping
        public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
            return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savePatient(patient));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
            return patientService.getPatientById(id)
                    .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping
        public List<Patient> getAllPatients() {
            return patientService.getAllPatients();
        }

        @PutMapping("/{id}")
        public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
            return patientService.getPatientById(id)
                    .map(patient -> {
                        patient.setName(patientDetails.getName());
                        patient.setEmail(patientDetails.getEmail());
                        patient.setPhone(patientDetails.getPhone());
                        patient.setInsuranceDetails(patientDetails.getInsuranceDetails());
                        return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.OK);
                    })
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
            if (patientService.getPatientById(id).isPresent()) {
                patientService.deletePatient(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    