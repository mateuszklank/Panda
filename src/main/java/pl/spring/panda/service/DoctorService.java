package pl.spring.panda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.spring.panda.model.Admission;
import pl.spring.panda.model.Doctor;
import pl.spring.panda.repository.AdmissionRepository;
import pl.spring.panda.repository.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public ResponseEntity<Doctor> createDoctor(Doctor doctorRequest) {
        Doctor doctor = doctorRepository.save(doctorRequest);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    public ResponseEntity<Doctor> getDoctorById(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalStateException("Not found doctor with id " + doctorId));

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteDoctor(Long doctor_id) {
        doctorRepository.deleteById(doctor_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
