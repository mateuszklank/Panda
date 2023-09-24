package pl.spring.panda.repository.jdbcrepository;

import pl.spring.panda.model.jdbcmodel.JdbcPatient;

import java.time.LocalDate;
import java.util.List;

public interface JdbcPatientRepository {

    int save(JdbcPatient patient);

    int update(JdbcPatient patient);

    JdbcPatient findById(Long id);

    JdbcPatient findByIdWithAdmissions(Long id);

    int deleteById(Long id);

    List<JdbcPatient> findAll(String lastName, LocalDate dateAfter);

    List<JdbcPatient> findByDateAfter(LocalDate dateAfter);

    List<JdbcPatient> findByLastNameContaining(String lastName);

    List<JdbcPatient> findByEmailContaining(String email);

    int deleteAll();
}
