package pl.spring.panda.model;

import jakarta.persistence.*;

@Entity
@Table
public class Province {

    @Id
    @SequenceGenerator(
            name = "province_sequence",
            sequenceName = "province_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "province_sequence"
    )
    private Long id;
    private String province_name;

    public Province() {
    }

    public Province(Long id, String province_name) {
        this.id = id;
        this.province_name = province_name;
    }

    public Province(String province_name) {
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

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", province_name='" + province_name + '\'' +
                '}';
    }
}
