    package com.example.medicalrecords_service.controller;

    import com.example.medicalrecords_service.model.MedicalRecord;
    import com.example.medicalrecords_service.service.MedicalRecordService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/records")
    public class MedicalRecordController {
        @Autowired
        private MedicalRecordService medicalRecordService;

        @PostMapping
        public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
            return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.saveMedicalRecord(medicalRecord));
        }

        @GetMapping("/{id}")
        public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable String id) {
            return medicalRecordService.getMedicalRecordById(id)
                    .map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping("/patient/{patientId}")
        public List<MedicalRecord> getMedicalRecordsByPatientId(@PathVariable Long patientId) {
            return medicalRecordService.getMedicalRecordsByPatientId(patientId);
        }

        @GetMapping
        public List<MedicalRecord> getAllMedicalRecords() {
            return medicalRecordService.getAllMedicalRecords();
        }
    }
    