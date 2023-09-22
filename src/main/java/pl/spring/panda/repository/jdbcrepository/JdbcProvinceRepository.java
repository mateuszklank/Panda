package pl.spring.panda.repository.jdbcrepository;

import pl.spring.panda.model.jdbcmodel.JdbcPatient;
import pl.spring.panda.model.jdbcmodel.JdbcProvince;

import java.util.List;

public interface JdbcProvinceRepository {

    List<JdbcProvince> findAll();

    List<JdbcProvince> findByProvinceNameContaining(String lastName);
}
