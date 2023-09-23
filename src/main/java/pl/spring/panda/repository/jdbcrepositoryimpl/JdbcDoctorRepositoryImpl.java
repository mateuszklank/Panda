package pl.spring.panda.repository.jdbcrepositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.panda.model.jdbcmodel.JdbcDoctor;
import pl.spring.panda.repository.jdbcrepository.JdbcDoctorRepository;

import java.util.List;

@Repository
public class JdbcDoctorRepositoryImpl implements JdbcDoctorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(JdbcDoctor doctor) {
        return jdbcTemplate.update("INSERT INTO zjdbc_doctor (first_name, last_name, specialty) VALUES(?,?,?)",
                new Object[] { doctor.getFirst_name(), doctor.getLast_name(), doctor.getSpecialty() });
    }

    @Override
    public int update(JdbcDoctor doctor) {
        return jdbcTemplate.update("UPDATE zjdbc_doctor SET first_name=?, last_name=?, specialty=? WHERE id=?",
                new Object[] { doctor.getFirst_name(), doctor.getLast_name(), doctor.getSpecialty(), doctor.getId() });
    }

    @Override
    public JdbcDoctor findById(Long id) {
        try {
            JdbcDoctor doctor = jdbcTemplate.queryForObject("SELECT * FROM zjdbc_doctor WHERE id=?",
                    BeanPropertyRowMapper.newInstance(JdbcDoctor.class), id);

            return doctor;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM zjdbc_doctor WHERE id=?", id);
    }

    @Override
    public List<JdbcDoctor> findAll() {
        return jdbcTemplate.query("SELECT * from zjdbc_doctor", BeanPropertyRowMapper.newInstance(JdbcDoctor.class));
    }

    @Override
    public List<JdbcDoctor> findByLastNameContaining(String lastName) {
        String q = "SELECT * from zjdbc_doctor WHERE last_name ILIKE '%" + lastName + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(JdbcDoctor.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from zjdbc_doctor");
    }
}
