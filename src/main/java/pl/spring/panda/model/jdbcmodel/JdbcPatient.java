package pl.spring.panda.model.jdbcmodel;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.spring.panda.model.Province;

import java.time.LocalDate;
import java.util.Set;

public class JdbcPatient {

    @Id
    private Long id;
    private String first_name;
    private String last_name;
    private String gender;
    private LocalDate birth_date;
    private String city;
    private Long province_id;
    private String allergies;
    private Double height;
    private Double weight;
    private String email;
    @MappedCollection(keyColumn = "id", idColumn = "id")
    private Set<JdbcAdmission> admissions;

    public JdbcPatient() {
    }

    public JdbcPatient(Long id, String first_name, String last_name, String gender, LocalDate birth_date, String city, Long province_id, String allergies, Double height, Double weight, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.city = city;
        this.province_id = province_id;
        this.allergies = allergies;
        this.height = height;
        this.weight = weight;
        this.email = email;
    }

    public JdbcPatient(String first_name, String last_name, String gender, LocalDate birth_date, String city, Long province_id, String allergies, Double height, Double weight, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.city = city;
        this.province_id = province_id;
        this.allergies = allergies;
        this.height = height;
        this.weight = weight;
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Long province_id) {
        this.province_id = province_id;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<JdbcAdmission> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(Set<JdbcAdmission> admissions) {
        this.admissions = admissions;
    }

    @Override
    public String toString() {
        return "JdbcPatient{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", birth_date=" + birth_date +
                ", city='" + city + '\'' +
                ", province_id=" + province_id +
                ", allergies='" + allergies + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", email='" + email + '\'' +
                ", admissions=" + admissions +
                '}';
    }
}
