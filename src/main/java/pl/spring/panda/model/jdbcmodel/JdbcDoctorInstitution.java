package pl.spring.panda.model.jdbcmodel;

import org.springframework.data.annotation.Id;

public class JdbcDoctorInstitution {

    @Id
    private Long id;
    private Long doctor_id;
    private Long institution_id;

    public JdbcDoctorInstitution() {
    }

    public JdbcDoctorInstitution(Long id, Long doctor_id, Long institution_id) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.institution_id = institution_id;
    }

    public JdbcDoctorInstitution(Long doctor_id, Long institution_id) {
        this.doctor_id = doctor_id;
        this.institution_id = institution_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Long getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(Long institution_id) {
        this.institution_id = institution_id;
    }

    @Override
    public String toString() {
        return "JdbcDoctorInstitution{" +
                "id=" + id +
                ", doctor_id=" + doctor_id +
                ", institution_id=" + institution_id +
                '}';
    }
}
