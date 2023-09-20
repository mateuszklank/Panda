package pl.spring.panda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.spring.panda.model.Admission;
import pl.spring.panda.model.Patient;
import pl.spring.panda.repository.AdmissionRepository;
import pl.spring.panda.repository.DoctorRepository;
import pl.spring.panda.repository.PatientRepository;

import java.util.List;

@Service
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public AdmissionService(AdmissionRepository admissionRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.admissionRepository = admissionRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Admission> getAdmissions() {
        return admissionRepository.findAll();
    }

    public ResponseEntity<List<Admission>> getAllAdmissionsByPatientId(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new IllegalStateException("Not found patient with id " + patientId);
        }

        List<Admission> admissions = admissionRepository.findByPatientId(patientId);
        return new ResponseEntity<>(admissions, HttpStatus.OK);
    }

    public ResponseEntity<Admission> getAdmissionById(Long admissionId) {
        Admission admission = admissionRepository.findById(admissionId)
                .orElseThrow(() -> new IllegalStateException("Not found admission with id " + admissionId));

        return new ResponseEntity<>(admission, HttpStatus.OK);
    }

    public ResponseEntity<Admission> createAdmission(Long patientId, Long doctorId, Admission admissionRequest) {

        patientRepository.findById(patientId).map(patient -> {
            admissionRequest.setPatient(patient);
            return true;
        }).orElseThrow(() -> new IllegalStateException("Not found patient with id " + patientId));

        doctorRepository.findById(doctorId).map(doctor -> {
            admissionRequest.setDoctor(doctor);
            return true;
        }).orElseThrow(() -> new IllegalStateException("Not found doctor with id " + doctorId));

        Admission admission = admissionRepository.save(admissionRequest);
        return new ResponseEntity<>(admission, HttpStatus.CREATED);
    }

    public ResponseEntity<Admission> updateAdmission(Long admission_id, Admission admissionRequest) {
        Admission admission = admissionRepository.findById(admission_id)
                .orElseThrow(() -> new IllegalStateException("Admission with id " + admission_id + " not found"));

        admission.setDiagnosis(admissionRequest.getDiagnosis());
        return new ResponseEntity<>(admissionRepository.save(admission), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteAdmission(Long admission_id) {
        admissionRepository.deleteById(admission_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Admission>> deleteAllAdmissionsOfPatient(Long patient_id) {

        if (!patientRepository.existsById(patient_id)) {
            throw new IllegalStateException("Not found patient with id " + patient_id);
        }

        admissionRepository.deleteByPatientId(patient_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
