package pl.spring.panda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.spring.panda.model.Patient;
import pl.spring.panda.repository.PatientRepository;
import pl.spring.panda.repository.ProvinceRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ProvinceRepository provinceRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, ProvinceRepository provinceRepository) {
        this.patientRepository = patientRepository;
        this.provinceRepository = provinceRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public ResponseEntity<Patient> addNewPatient(Long provinceId, Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findPatientByEmail(patient.getEmail());
        if (patientOptional.isPresent()) {
            throw new IllegalStateException("An patient with this email already exists");
        }

        provinceRepository.findById(provinceId).map(province -> {
            patient.setProvince(province);
            return true;
        }).orElseThrow(() -> new IllegalStateException("Not found province with id " + provinceId));

        patientRepository.save(patient);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    public void deletePatient(Long patientId) {
        boolean exists = patientRepository.existsById(patientId);
        if (!exists) {
            throw new IllegalStateException("An patient with id " + patientId + " does not exists");
        }
        patientRepository.deleteById(patientId);
    }

    @Transactional
    public void updatePatient(Long patientId, String firstName, String lastName, String email) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalStateException("An patient with id " + patientId + " does not exists"));
        if (firstName != null && !firstName.isEmpty() && !Objects.equals(patient.getFirst_name(), firstName)) {
            patient.setFirst_name(firstName);
        }
        if (lastName != null && !lastName.isEmpty() && !Objects.equals(patient.getLast_name(), lastName)) {
            patient.setLast_name(lastName);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(patient.getEmail(), email)) {
            Optional<Patient> patientOptional = patientRepository.findPatientByEmail(email);
            if (patientOptional.isPresent()) {
                throw new IllegalStateException("An patient with this email already exists");
            }
            patient.setEmail(email);
        }
    }
}
