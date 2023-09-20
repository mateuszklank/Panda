package pl.spring.panda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.spring.panda.model.Doctor;
import pl.spring.panda.model.Patient;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
