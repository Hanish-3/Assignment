    package com.example.doctor_service.service;

    import com.example.doctor_service.model.Doctor;
    import com.example.doctor_service.repository.DoctorRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class DoctorService {
        @Autowired
        private DoctorRepository doctorRepository;

        public Doctor saveDoctor(Doctor doctor) {
            return doctorRepository.save(doctor);
        }

        public Optional<Doctor> getDoctorById(Long id) {
            return doctorRepository.findById(id);
        }

        public List<Doctor> getAllDoctors() {
            return doctorRepository.findAll();
        }

        public void deleteDoctor(Long id) {
            doctorRepository.deleteById(id);
        }
    }
    