package pl.spring.panda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.spring.panda.model.Patient;
import pl.spring.panda.model.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
}
