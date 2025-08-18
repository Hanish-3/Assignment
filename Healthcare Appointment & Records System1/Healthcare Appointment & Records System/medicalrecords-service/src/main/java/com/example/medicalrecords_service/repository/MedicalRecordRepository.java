    package com.example.medicalrecords_service.repository;

    import com.example.medicalrecords_service.model.MedicalRecord;
    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
        List<MedicalRecord> findByPatientId(Long patientId);
        List<MedicalRecord> findByDoctorId(Long doctorId);
    }
    