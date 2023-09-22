package pl.spring.panda.model.jdbcmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;

public class JdbcProvince {

    @Id
    private Long id;
    private String province_name;
    @MappedCollection(keyColumn = "id", idColumn = "id")
    private Set<JdbcPatient> patients;

    public JdbcProvince() {
    }

    public JdbcProvince(Long id, String province_name) {
        this.id = id;
        this.province_name = province_name;
    }

    public JdbcProvince(String province_name) {
        this.province_name = province_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public Set<JdbcPatient> getPatients() {
        return patients;
    }

    public void setPatients(Set<JdbcPatient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "JdbcProvince{" +
                "id=" + id +
                ", province_name='" + province_name + '\'' +
                ", patients=" + patients +
                '}';
    }
}
