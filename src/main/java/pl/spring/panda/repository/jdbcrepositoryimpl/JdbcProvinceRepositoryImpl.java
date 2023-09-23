package pl.spring.panda.repository.jdbcrepositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.spring.panda.model.jdbcmodel.JdbcPatient;
import pl.spring.panda.model.jdbcmodel.JdbcProvince;
import pl.spring.panda.repository.jdbcrepository.JdbcProvinceRepository;

import java.util.List;

@Repository
public class JdbcProvinceRepositoryImpl implements JdbcProvinceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<JdbcProvince> findAll() {
        return jdbcTemplate.query("SELECT * from zjdbc_province", BeanPropertyRowMapper.newInstance(JdbcProvince.class));
    }

    @Override
    public List<JdbcProvince> findByProvinceNameContaining(String provinceName) {
        String q = "SELECT * from zjdbc_province WHERE province_name ILIKE '%" + provinceName + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(JdbcProvince.class));
    }

    @Override
    public JdbcProvince findById(Long id) {
        try {
            JdbcProvince province = jdbcTemplate.queryForObject("SELECT * FROM zjdbc_province WHERE id=?",
                    BeanPropertyRowMapper.newInstance(JdbcProvince.class), id);

            return province;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
