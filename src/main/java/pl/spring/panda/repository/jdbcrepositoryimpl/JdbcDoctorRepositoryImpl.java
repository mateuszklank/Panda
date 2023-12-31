package pl.spring.panda.repository.jdbcrepositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.panda.model.jdbcmodel.*;
import pl.spring.panda.repository.jdbcrepository.JdbcDoctorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

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
    public JdbcDoctor findByIdWithInstitutions(Long id) {
        try {
            JdbcDoctor doctor = jdbcTemplate.queryForObject("SELECT * FROM zjdbc_doctor WHERE id=?",
                    BeanPropertyRowMapper.newInstance(JdbcDoctor.class), id);
            List<JdbcDoctorInstitution> doctorInstitutionList = jdbcTemplate.query("SELECT * FROM zjdbc_doctor_institution WHERE doctor_id=?",
                    BeanPropertyRowMapper.newInstance(JdbcDoctorInstitution.class), id);
            Set<JdbcInstitution> institutions = new java.util.HashSet<>(Collections.emptySet());
            for (final JdbcDoctorInstitution doctorInstitution : doctorInstitutionList) {
                Long institution_id = doctorInstitution.getInstitution_id();
                JdbcInstitution institution = jdbcTemplate.queryForObject("SELECT * FROM zjdbc_institution WHERE id=?",
                        BeanPropertyRowMapper.newInstance(JdbcInstitution.class), institution_id);
                institutions.add(institution);
            }
            if (doctor != null) {
                doctor.setDoctorInstitutionSet(Set.copyOf(doctorInstitutionList));
                doctor.setInstitutions(institutions);
            }
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
