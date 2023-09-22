package pl.spring.panda.controller.jdbccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spring.panda.model.jdbcmodel.JdbcAdmission;
import pl.spring.panda.model.jdbcmodel.JdbcDoctor;
import pl.spring.panda.model.jdbcmodel.JdbcPatient;
import pl.spring.panda.model.jdbcmodel.JdbcProvince;
import pl.spring.panda.repository.jdbcrepository.JdbcAdmissionRepository;
import pl.spring.panda.repository.jdbcrepository.JdbcDoctorRepository;
import pl.spring.panda.repository.jdbcrepository.JdbcPatientRepository;
import pl.spring.panda.repository.jdbcrepository.JdbcProvinceRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/jdbc")
public class JdbcAdmissionController {

    @Autowired
    JdbcPatientRepository jdbcPatientRepository;

    @Autowired
    JdbcDoctorRepository jdbcDoctorRepository;

    @Autowired
    JdbcAdmissionRepository jdbcAdmissionRepository;

    @GetMapping("/admissions")
    public ResponseEntity<List<JdbcAdmission>> getAllAdmissions() {
        try {
            List<JdbcAdmission> admissions = new ArrayList<JdbcAdmission>();
            jdbcAdmissionRepository.findAll().forEach(admissions::add);
            if (admissions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(admissions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/admission/{id}")
    public ResponseEntity<JdbcAdmission> getAdmissionById(@PathVariable("id") Long id) {
        JdbcAdmission admission = jdbcAdmissionRepository.findById(id);

        if (admission != null) {
            return new ResponseEntity<>(admission, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admission")
    public ResponseEntity<String> createAdmission(@RequestBody JdbcAdmission admission) {
        JdbcPatient _patient = jdbcPatientRepository.findById(admission.getPatient_id());
        JdbcDoctor _doctor = jdbcDoctorRepository.findById(admission.getDoctor_id());
        if (_doctor != null) {
            if (_patient != null) {
                try {
                    jdbcAdmissionRepository.save(new JdbcAdmission(admission.getAdmission_date(), admission.getDischarge_date(), admission.getDiagnosis(), admission.getPatient_id(), admission.getDoctor_id()));
                    return new ResponseEntity<>("Admission created " + admission, HttpStatus.CREATED);
                } catch (Exception e) {
                    return new ResponseEntity<>("Can't create admission " + admission, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>("Not found patient with id " + admission.getPatient_id(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Not found doctor with id " + admission.getDoctor_id(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/admission/{id}")
    public ResponseEntity<String> updateAdmission(@PathVariable("id") Long id, @RequestBody JdbcAdmission admission) {
        JdbcAdmission _admission = jdbcAdmissionRepository.findById(id);

        if (_admission != null) {
            _admission.setId(id);
            _admission.setDiagnosis(admission.getDiagnosis());

            jdbcAdmissionRepository.update(_admission);
            return new ResponseEntity<>("Admission was updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find admission with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admission/{id}")
    public ResponseEntity<String> deleteAdmission(@PathVariable("id") Long id) {
        try {
            int result = jdbcAdmissionRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find admission with id " + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Admission was deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete admission", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/admissions/delete")
    public ResponseEntity<String> deleteAllAdmissions() {
        try {
            int numRows = jdbcAdmissionRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " admission(s) successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete admission(s)", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
