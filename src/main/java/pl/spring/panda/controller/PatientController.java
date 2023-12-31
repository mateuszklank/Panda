package pl.spring.panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spring.panda.model.Patient;
import pl.spring.panda.service.PatientService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping("/province/{provinceId}")
    public ResponseEntity<Patient> registerNewPatient (@PathVariable(value = "provinceId") Long provinceId, @RequestBody Patient patient) {
        return patientService.addNewPatient(provinceId, patient);
    }

    @DeleteMapping(path = "{patientId}")
    public void deletePatient (@PathVariable("patientId") Long patientId) {
        patientService.deletePatient(patientId);
    }

    @PutMapping(path = "{patientId}")
    public void updatePatient (@PathVariable("patientId") Long patientId,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) String email) {
        patientService.updatePatient(patientId, firstName, lastName, email);
    }
}
