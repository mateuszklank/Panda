package pl.spring.panda.repository.jdbcrepositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.panda.model.jdbcmodel.JdbcAdmission;
import pl.spring.panda.model.jdbcmodel.JdbcDoctor;
import pl.spring.panda.model.jdbcmodel.JdbcPatient;
import pl.spring.panda.repository.jdbcrepository.JdbcAdmissionRepository;

import java.util.List;

@Repository
public class JdbcAdmissionRepositoryImpl implements JdbcAdmissionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(JdbcAdmission admission) {
        return jdbcTemplate.update("INSERT INTO zjdbc_admission (admission_date, discharge_date, diagnosis, patient_id, doctor_id) VALUES(?,?,?,?,?)",
                new Object[] { admission.getAdmission_date(), admission.getDischarge_date(), admission.getDiagnosis(), admission.getPatient_id(), admission.getDoctor_id() });
    }

    @Override
    public int update(JdbcAdmission admission) {
        return jdbcTemplate.update("UPDATE zjdbc_admission SET diagnosis=? WHERE id=?",
                new Object[] { admission.getDiagnosis(), admission.getId() });
    }

    @Override
    public JdbcAdmission findById(Long id) {
        try {
            JdbcAdmission admission = jdbcTemplate.queryForObject("SELECT * FROM zjdbc_admission WHERE id=?",
                    BeanPropertyRowMapper.newInstance(JdbcAdmission.class), id);

            return admission;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM zjdbc_admission WHERE id=?", id);
    }

    @Override
    public List<JdbcAdmission> findAll() {
        return jdbcTemplate.query("SELECT * from zjdbc_admission", BeanPropertyRowMapper.newInstance(JdbcAdmission.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from zjdbc_admission");
    }
}
