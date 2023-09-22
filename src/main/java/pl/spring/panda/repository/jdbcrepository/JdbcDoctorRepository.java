package pl.spring.panda.repository.jdbcrepository;

import pl.spring.panda.model.jdbcmodel.JdbcDoctor;

import java.util.List;

public interface JdbcDoctorRepository {

    int save(JdbcDoctor doctor);

    int update(JdbcDoctor doctor);

    JdbcDoctor findById(Long id);

    int deleteById(Long id);

    List<JdbcDoctor> findAll();

    List<JdbcDoctor> findByLastNameContaining(String lastName);

    int deleteAll();
}
