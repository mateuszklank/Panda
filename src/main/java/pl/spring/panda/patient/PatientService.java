package pl.spring.panda.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
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
}
