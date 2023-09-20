package pl.spring.panda.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.spring.panda.model.Doctor;
import pl.spring.panda.model.Patient;
import pl.spring.panda.repository.DoctorRepository;
import pl.spring.panda.repository.PatientRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class DoctorConfig {

    @Bean
    CommandLineRunner commandLineRunner2(DoctorRepository repository) {
        return args -> {

            Doctor adam = new Doctor(
                    "Adam",
                    "Przykładowy",
                    "okulista"
            );

            Doctor renata = new Doctor(
                    "Renata",
                    "Przykładowa",
                    "alergolog"
            );

//            repository.saveAll(List.of(adam, renata));
        };
    }

}
