package pl.spring.panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spring.panda.model.Admission;
import pl.spring.panda.repository.AdmissionRepository;
import pl.spring.panda.repository.DoctorRepository;
import pl.spring.panda.repository.PatientRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AdmissionController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/patients/{patientId}/admissions")
    public ResponseEntity<List<Admission>> getAllAdmissionsByPatientId(@PathVariable(value = "patientId") Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new IllegalStateException("Not found patient with id " + patientId);
        }

        List<Admission> admissions = admissionRepository.findByPatientId(patientId);
        return new ResponseEntity<>(admissions, HttpStatus.OK);
    }

    @GetMapping("/admissions/{admissionId}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable(value = "admissionId") Long admissionId) {
        Admission admission = admissionRepository.findById(admissionId)
                .orElseThrow(() -> new IllegalStateException("Not found admission with id " + admissionId));

        return new ResponseEntity<>(admission, HttpStatus.OK);
    }

    @PostMapping("/patients/{patientId}/{doctorId}/admissions")
    public ResponseEntity<Admission> createAdmission(@PathVariable(value = "patientId") Long patientId, @PathVariable(value = "doctorId") Long doctorId, @RequestBody Admission admissionRequest) {

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

    @PutMapping("/admissions/{admissionId}")
    public ResponseEntity<Admission> updateAdmission(@PathVariable("admissionId") long admission_id, @RequestBody Admission admissionRequest) {
        Admission admission = admissionRepository.findById(admission_id)
                .orElseThrow(() -> new IllegalStateException("Admission with id " + admission_id + " not found"));

        admission.setDiagnosis(admissionRequest.getDiagnosis());

        return new ResponseEntity<>(admissionRepository.save(admission), HttpStatus.OK);
    }

    @DeleteMapping("/admissions/{admissionId}")
    public ResponseEntity<HttpStatus> deleteAdmission(@PathVariable("admissionId") long admission_id) {
        admissionRepository.deleteById(admission_id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/patients/{patientId}/admissions")
    public ResponseEntity<List<Admission>> deleteAllAdmissionsOfPatient(@PathVariable(value = "patientId") Long patient_id) {
        if (!patientRepository.existsById(patient_id)) {
            throw new IllegalStateException("Not found patient with id " + patient_id);
        }

        admissionRepository.deleteByPatientId(patient_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
