package pl.spring.panda.repository.jdbcrepositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.panda.model.jdbcmodel.JdbcAdmission;
import pl.spring.panda.model.jdbcmodel.JdbcPatient;
import pl.spring.panda.repository.jdbcrepository.JdbcPatientRepository;

import java.util.List;
import java.util.Set;

@Repository
public class JdbcPatientRepositoryImpl implements JdbcPatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(JdbcPatient patient) {
        return jdbcTemplate.update("INSERT INTO zjdbc_patient (first_name, last_name, gender, birth_date, city, province_id, allergies, height, weight, email) VALUES(?,?,?,?,?,?,?,?,?,?)",
                new Object[] { patient.getFirst_name(), patient.getLast_name(), patient.getGender(), patient.getBirth_date(), patient.getCity(), patient.getProvince_id(), patient.getAllergies(), patient.getHeight(), patient.getWeight(), patient.getEmail() });
    }

    @Override
    public int update(JdbcPatient patient) {
        return jdbcTemplate.update("UPDATE zjdbc_patient SET first_name=?, last_name=?, gender=? WHERE id=?",
                new Object[] { patient.getFirst_name(), patient.getLast_name(), patient.getGender(), patient.getId() });
    }

    @Override
    public JdbcPatient findById(Long id) {
        try {
            JdbcPatient patient = jdbcTemplate.queryForObject("SELECT * FROM zjdbc_patient WHERE id=?",
                    BeanPropertyRowMapper.newInstance(JdbcPatient.class), id);

            return patient;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public JdbcPatient findByIdWithAdmissions(Long id) {
        try {
            JdbcPatient patient = jdbcTemplate.queryForObject("SELECT * FROM zjdbc_patient WHERE id=?",
                    BeanPropertyRowMapper.newInstance(JdbcPatient.class), id);
            List<JdbcAdmission> admissions = jdbcTemplate.query("SELECT * FROM zjdbc_admission WHERE patient_id=?",
                    BeanPropertyRowMapper.newInstance(JdbcAdmission.class), id);
            if (patient != null) {
                patient.setAdmissions(Set.copyOf(admissions));
            }
            return patient;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM zjdbc_patient WHERE id=?", id);
    }

    @Override
    public List<JdbcPatient> findAll() {
        return jdbcTemplate.query("SELECT * from zjdbc_patient", BeanPropertyRowMapper.newInstance(JdbcPatient.class));
    }

    @Override
    public List<JdbcPatient> findByLastNameContaining(String lastName) {
        String q = "SELECT * from zjdbc_patient WHERE last_name ILIKE '%" + lastName + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(JdbcPatient.class));
    }

    @Override
    public List<JdbcPatient> findByEmailContaining(String email) {
        String q = "SELECT * from zjdbc_patient WHERE last_name ILIKE '%" + email + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(JdbcPatient.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from zjdbc_patient");
    }
}
