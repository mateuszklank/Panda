package pl.spring.panda.repository.jdbcrepository;

import pl.spring.panda.model.jdbcmodel.JdbcProvince;

import java.util.List;

public interface JdbcProvinceRepository {

    List<JdbcProvince> findAll();

    JdbcProvince findById(Long id);

    List<JdbcProvince> findByProvinceNameContaining(String lastName);
}
