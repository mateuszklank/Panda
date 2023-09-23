package pl.spring.panda.controller.jdbccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spring.panda.model.jdbcmodel.JdbcDoctor;
import pl.spring.panda.model.jdbcmodel.JdbcPatient;
import pl.spring.panda.model.jdbcmodel.JdbcProvince;
import pl.spring.panda.repository.jdbcrepository.JdbcPatientRepository;
import pl.spring.panda.repository.jdbcrepository.JdbcProvinceRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/jdbc")
public class JdbcPatientController {

    @Autowired
    JdbcPatientRepository jdbcPatientRepository;

    @Autowired
    JdbcProvinceRepository jdbcProvinceRepository;

    @GetMapping("/patients")
    public ResponseEntity<List<JdbcPatient>> getAllPatients(@RequestParam(required = false) String lastName) {
        try {
            List<JdbcPatient> patients = new ArrayList<JdbcPatient>();

            if (lastName == null)
                jdbcPatientRepository.findAll().forEach(patients::add);
            else
                jdbcPatientRepository.findByLastNameContaining(lastName).forEach(patients::add);

            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<JdbcPatient> getPatientById(@PathVariable("id") Long id, @RequestParam(required = false) Boolean admission) {
        JdbcPatient patient;
        if (admission) {
            patient = jdbcPatientRepository.findByIdWithAdmissions(id);
        } else {
            patient = jdbcPatientRepository.findById(id);
        }
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/patient")
    public ResponseEntity<String> createPatient(@RequestBody JdbcPatient patient) {
        JdbcProvince _province = jdbcProvinceRepository.findById(patient.getProvince_id());
        if (_province != null) {
            try {
                jdbcPatientRepository.save(new JdbcPatient(patient.getFirst_name(), patient.getLast_name(), patient.getGender(), patient.getBirth_date(), patient.getCity(), patient.getProvince_id(), patient.getAllergies(), patient.getHeight(), patient.getWeight(), patient.getEmail()));
                return new ResponseEntity<>("Patient created " + patient, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Can't create patient " + patient, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Not found province with id " + patient.getProvince_id(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable("id") Long id, @RequestBody JdbcPatient patient) {
        JdbcPatient _patient = jdbcPatientRepository.findById(id);

        if (_patient != null) {
            _patient.setId(id);
            _patient.setFirst_name(patient.getFirst_name());
            _patient.setLast_name(patient.getLast_name());
            _patient.setGender(patient.getGender());

            jdbcPatientRepository.update(_patient);
            return new ResponseEntity<>("Patient was updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find patient with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Long id) {
        try {
            int result = jdbcPatientRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find patient with id " + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Patient was deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/patients/delete")
    public ResponseEntity<String> deleteAllPatients() {
        try {
            int numRows = jdbcPatientRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " patient(s) successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete patient(s)", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
