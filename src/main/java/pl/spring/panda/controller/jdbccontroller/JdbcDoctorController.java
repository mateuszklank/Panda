package pl.spring.panda.controller.jdbccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spring.panda.model.jdbcmodel.JdbcDoctor;
import pl.spring.panda.model.jdbcmodel.JdbcDoctorInstitution;
import pl.spring.panda.repository.jdbcrepository.JdbcDoctorRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/jdbc")
public class JdbcDoctorController {

    @Autowired
    JdbcDoctorRepository jdbcDoctorRepository;

    @GetMapping("/doctors")
    public ResponseEntity<List<JdbcDoctor>> getAllDoctors(@RequestParam(required = false) String lastName) {
        try {
            List<JdbcDoctor> doctors = new ArrayList<JdbcDoctor>();

            if (lastName == null)
                jdbcDoctorRepository.findAll().forEach(doctors::add);
            else
                jdbcDoctorRepository.findByLastNameContaining(lastName).forEach(doctors::add);

            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<JdbcDoctor> getDoctorById(@PathVariable("id") Long id, @RequestParam(required = false) Boolean institution) {
        JdbcDoctor doctor;
        if (institution) {
            doctor = jdbcDoctorRepository.findByIdWithInstitutions(id);
        } else {
            doctor = jdbcDoctorRepository.findById(id);
        }
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/doctor")
    public ResponseEntity<JdbcDoctor> createDoctor(@RequestBody JdbcDoctor doctor) {
        try {
            jdbcDoctorRepository.save(new JdbcDoctor(doctor.getFirst_name(), doctor.getLast_name(), doctor.getSpecialty()));
            return new ResponseEntity<>(doctor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(doctor, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/doctor/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable("id") Long id, @RequestBody JdbcDoctor doctor) {
        JdbcDoctor _doctor = jdbcDoctorRepository.findById(id);

        if (_doctor != null) {
            _doctor.setId(id);
            _doctor.setFirst_name(doctor.getFirst_name());
            _doctor.setLast_name(doctor.getLast_name());
            _doctor.setSpecialty(doctor.getSpecialty());

            jdbcDoctorRepository.update(_doctor);
            return new ResponseEntity<>("Doctor was updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find doctor with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") Long id) {
        try {
            int result = jdbcDoctorRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find doctor with id " + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Doctor was deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete doctor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/doctors/delete")
    public ResponseEntity<String> deleteAllDoctors() {
        try {
            int numRows = jdbcDoctorRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " doctor(s) successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete doctor(s)", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
