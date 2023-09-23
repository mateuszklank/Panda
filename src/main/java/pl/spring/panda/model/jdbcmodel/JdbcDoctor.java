package pl.spring.panda.model.jdbcmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;

public class JdbcDoctor {

    @Id
    private Long id;
    private String first_name;
    private String last_name;
    private String specialty;
    @MappedCollection(keyColumn = "id", idColumn = "id")
    private Set<JdbcAdmission> admissions;
    @JsonIgnore
    @MappedCollection(keyColumn = "id", idColumn = "id")
    private Set<JdbcDoctorInstitution> doctorInstitutionSet;
    @MappedCollection(keyColumn = "id", idColumn = "id")
    private Set<JdbcInstitution> institutions;

    public JdbcDoctor() {
    }

    public JdbcDoctor(Long id, String first_name, String last_name, String specialty) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialty = specialty;
    }

    public JdbcDoctor(String first_name, String last_name, String specialty) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.specialty = specialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Set<JdbcAdmission> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(Set<JdbcAdmission> admissions) {
        this.admissions = admissions;
    }

    public Set<JdbcDoctorInstitution> getDoctorInstitutionSet() {
        return doctorInstitutionSet;
    }

    public void setDoctorInstitutionSet(Set<JdbcDoctorInstitution> doctorInstitutionSet) {
        this.doctorInstitutionSet = doctorInstitutionSet;
    }

    public Set<JdbcInstitution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(Set<JdbcInstitution> institutions) {
        this.institutions = institutions;
    }

    @Override
    public String toString() {
        return "JdbcDoctor{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", admissions=" + admissions +
                ", doctorInstitutionSet=" + doctorInstitutionSet +
                ", institutions=" + institutions +
                '}';
    }
}
