package pl.spring.panda.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public void addNewPatient(Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findPatientByEmail(patient.getEmail());
        if (patientOptional.isPresent()) {
            throw new IllegalStateException("An patient with this email already exists");
        }
        patientRepository.save(patient);
        System.out.println(patient);
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
