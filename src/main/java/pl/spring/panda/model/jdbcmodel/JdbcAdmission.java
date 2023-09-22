package pl.spring.panda.model.jdbcmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;
import pl.spring.panda.model.Doctor;
import pl.spring.panda.model.Patient;

import java.time.LocalDate;

public class JdbcAdmission {

    @Id
    private Long admission_id;
    private Patient patient;
    private LocalDate admission_date;
    private LocalDate discharge_date;
    private String diagnosis;
    private Doctor doctor;
}
