package pl.spring.panda.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.spring.panda.model.Admission;
import pl.spring.panda.model.Patient;

import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {

    List<Admission> findByPatientId(Long patientId);

    @Transactional
    void deleteByPatientId(Long patientId);
}
