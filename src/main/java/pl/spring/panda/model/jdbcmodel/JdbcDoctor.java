package pl.spring.panda.model.jdbcmodel;

public class JdbcDoctor {

    private Long id;
    private String first_name;
    private String last_name;
    private String specialty;

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

    @Override
    public String toString() {
        return "jdbcDoctor{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
