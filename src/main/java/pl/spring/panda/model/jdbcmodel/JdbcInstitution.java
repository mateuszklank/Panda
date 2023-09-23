package pl.spring.panda.model.jdbcmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;

public class JdbcInstitution {

    @Id
    private Long id;
    private String institution_name;
    @JsonIgnore
    @MappedCollection(keyColumn = "id", idColumn = "id")
    private Set<JdbcDoctorInstitution> doctorInstitutionSet;

    public JdbcInstitution() {
    }

    public JdbcInstitution(Long id, String institution_name) {
        this.id = id;
        this.institution_name = institution_name;
    }

    public JdbcInstitution(String institution_name) {
        this.institution_name = institution_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public Set<JdbcDoctorInstitution> getDoctorInstitutionSet() {
        return doctorInstitutionSet;
    }

    public void setDoctorInstitutionSet(Set<JdbcDoctorInstitution> doctorInstitutionSet) {
        this.doctorInstitutionSet = doctorInstitutionSet;
    }

    @Override
    public String toString() {
        return "JdbcInstitution{" +
                "id=" + id +
                ", institution_name='" + institution_name + '\'' +
                ", doctorInstitutionSet=" + doctorInstitutionSet +
                '}';
    }
}
