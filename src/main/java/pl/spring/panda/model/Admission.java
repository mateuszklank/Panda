package pl.spring.panda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table
public class Admission {

    @Id
    @SequenceGenerator(
            name = "admission_sequence",
            sequenceName = "admission_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admission_sequence"
    )
    private Long admission_id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Patient patient;
    private LocalDate admission_date;
    private LocalDate discharge_date;
    private String diagnosis;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Doctor doctor;

    public Admission() {
    }

    public Admission(Long admission_id, Patient patient, LocalDate admission_date, LocalDate discharge_date, String diagnosis, Doctor doctor) {
        this.admission_id = admission_id;
        this.patient = patient;
        this.admission_date = admission_date;
        this.discharge_date = discharge_date;
        this.diagnosis = diagnosis;
        this.doctor = doctor;
    }

    public Admission(Patient patient, LocalDate admission_date, LocalDate discharge_date, String diagnosis, Doctor doctor) {
        this.patient = patient;
        this.admission_date = admission_date;
        this.discharge_date = discharge_date;
        this.diagnosis = diagnosis;
        this.doctor = doctor;
    }

    public Long getAdmission_id() {
        return admission_id;
    }

    public void setAdmission_id(Long admission_id) {
        this.admission_id = admission_id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(LocalDate admission_date) {
        this.admission_date = admission_date;
    }

    public LocalDate getDischarge_date() {
        return discharge_date;
    }

    public void setDischarge_date(LocalDate discharge_date) {
        this.discharge_date = discharge_date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Admission{" +
                "admission_id=" + admission_id +
                ", patient=" + patient +
                ", admission_date=" + admission_date +
                ", discharge_date=" + discharge_date +
                ", diagnosis='" + diagnosis + '\'' +
                ", doctor=" + doctor +
                '}';
    }
}
