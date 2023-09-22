package pl.spring.panda.repository.jdbcrepository;

import pl.spring.panda.model.jdbcmodel.JdbcAdmission;

import java.util.List;

public interface JdbcAdmissionRepository {

    int save(JdbcAdmission admission);

    int update(JdbcAdmission admission);

    JdbcAdmission findById(Long id);

    int deleteById(Long id);

    List<JdbcAdmission> findAll();

    int deleteAll();
}
