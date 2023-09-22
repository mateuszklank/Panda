package pl.spring.panda.model.jdbcmodel;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class JdbcAdmission {

    @Id
    private Long id;
    private LocalDate admission_date;
    private LocalDate discharge_date;
    private String diagnosis;
    private Long patient_id;
    private Long doctor_id;

    public JdbcAdmission() {
    }

    public JdbcAdmission(Long id, LocalDate admission_date, LocalDate discharge_date, String diagnosis, Long patient_id, Long doctor_id) {
        this.id = id;
        this.admission_date = admission_date;
        this.discharge_date = discharge_date;
        this.diagnosis = diagnosis;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
    }

    public JdbcAdmission(LocalDate admission_date, LocalDate discharge_date, String diagnosis, Long patient_id, Long doctor_id) {
        this.admission_date = admission_date;
        this.discharge_date = discharge_date;
        this.diagnosis = diagnosis;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    @Override
    public String toString() {
        return "JdbcAdmission{" +
                "admission_id=" + id +
                ", admission_date=" + admission_date +
                ", discharge_date=" + discharge_date +
                ", diagnosis='" + diagnosis + '\'' +
                ", patient_id=" + patient_id +
                ", doctor_id=" + doctor_id +
                '}';
    }
}
