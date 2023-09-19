package pl.spring.panda.patient;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table
public class Patient {

    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long patient_id;
    private String first_name;
    private String last_name;
    private String gender;
    private LocalDate birth_date;
    private String city;
    private Long province_id;
    private String allergies;
    private Double height;
    private Double weight;

    public Patient() {
    }

    public Patient(Long patient_id, String first_name, String last_name, String gender, LocalDate birth_date, String city, Long province_id, String allergies, Double height, Double weight) {
        this.patient_id = patient_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.city = city;
        this.province_id = province_id;
        this.allergies = allergies;
        this.height = height;
        this.weight = weight;
    }

    public Patient(String first_name, String last_name, String gender, LocalDate birth_date, String city, Long province_id, String allergies, Double height, Double weight) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.city = city;
        this.province_id = province_id;
        this.allergies = allergies;
        this.height = height;
        this.weight = weight;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
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

    @Override
    public String toString() {
        return "Patient{" +
                "patient_id=" + patient_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", birth_date=" + birth_date +
                ", city='" + city + '\'' +
                ", province_id=" + province_id +
                ", allergies='" + allergies + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
