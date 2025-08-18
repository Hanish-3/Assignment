    package com.example.doctor_service.controller;

    import com.example.doctor_service.model.Doctor;
    import com.example.doctor_service.service.DoctorService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/doctors")
    public class DoctorController {
        @Autowired
        private DoctorService doctorService;

        @PostMapping
        public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
            return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.saveDoctor(doctor));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
            return doctorService.getDoctorById(id)
                    .map(doctor -> new ResponseEntity<>(doctor, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping
        public List<Doctor> getAllDoctors() {
            return doctorService.getAllDoctors();
        }

        @PutMapping("/{id}")
        public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
            return doctorService.getDoctorById(id)
                    .map(doctor -> {
                        doctor.setName(doctorDetails.getName());
                        doctor.setSpecialization(doctorDetails.getSpecialization());
                        doctor.setAvailableFrom(doctorDetails.getAvailableFrom());
                        doctor.setAvailableTo(doctorDetails.getAvailableTo());
                        return new ResponseEntity<>(doctorService.saveDoctor(doctor), HttpStatus.OK);
                    })
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
            if (doctorService.getDoctorById(id).isPresent()) {
                doctorService.deleteDoctor(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    