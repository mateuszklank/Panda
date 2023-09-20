package pl.spring.panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spring.panda.model.Admission;
import pl.spring.panda.repository.AdmissionRepository;
import pl.spring.panda.repository.DoctorRepository;
import pl.spring.panda.repository.PatientRepository;
import pl.spring.panda.service.AdmissionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AdmissionController {

    private final AdmissionService admissionService;

    @Autowired AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @GetMapping("/patients/{patientId}/admissions")
    public ResponseEntity<List<Admission>> getAllAdmissionsByPatientId(@PathVariable(value = "patientId") Long patientId) {
        return admissionService.getAllAdmissionsByPatientId(patientId);
    }

    @GetMapping("/admissions/{admissionId}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable(value = "admissionId") Long admissionId) {
        return admissionService.getAdmissionById(admissionId);
    }

    @PostMapping("/patients/{patientId}/{doctorId}/admissions")
    public ResponseEntity<Admission> createAdmission(@PathVariable(value = "patientId") Long patientId, @PathVariable(value = "doctorId") Long doctorId, @RequestBody Admission admissionRequest) {
        return admissionService.createAdmission(patientId, doctorId, admissionRequest);
    }

    @PutMapping("/admissions/{admissionId}")
    public ResponseEntity<Admission> updateAdmission(@PathVariable("admissionId") Long admission_id, @RequestBody Admission admissionRequest) {
        return admissionService.updateAdmission(admission_id, admissionRequest);
    }

    @DeleteMapping("/admissions/{admissionId}")
    public ResponseEntity<HttpStatus> deleteAdmission(@PathVariable("admissionId") Long admission_id) {
        return admissionService.deleteAdmission(admission_id);
    }

    @DeleteMapping("/patients/{patientId}/admissions")
    public ResponseEntity<List<Admission>> deleteAllAdmissionsOfPatient(@PathVariable(value = "patientId") Long patient_id) {
        return admissionService.deleteAllAdmissionsOfPatient(patient_id);
    }
}
