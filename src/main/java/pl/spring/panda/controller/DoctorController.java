package pl.spring.panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spring.panda.model.Admission;
import pl.spring.panda.model.Doctor;
import pl.spring.panda.model.Patient;
import pl.spring.panda.service.AdmissionService;
import pl.spring.panda.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctorRequest) {
        return doctorService.createDoctor(doctorRequest);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "doctorId") Long doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable("doctorId") Long doctor_id) {
        return doctorService.deleteDoctor(doctor_id);
    }
}
