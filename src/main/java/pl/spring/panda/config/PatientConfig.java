package pl.spring.panda.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.spring.panda.model.Patient;
import pl.spring.panda.repository.PatientRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner1(PatientRepository repository) {
        return args -> {

//            Patient jan = new Patient(
//                    "Jan",
//                    "Kowalski",
//                    "m",
//                    LocalDate.of(1980, Month.APRIL, 15),
//                    "Warszawa",
//                    1L,
//                    "mleko",
//                    180d,
//                    80d,
//                    "jankowalski@gmail.com"
//            );
//
//            Patient andrzej = new Patient(
//                    "Andrzej",
//                    "Nowak",
//                    "m",
//                    LocalDate.of(1960, Month.JUNE, 25),
//                    "Warszawa",
//                    1L,
//                    "orzechy",
//                    170d,
//                    70d,
//                    "andrzejnowak@gmail.com"
//            );

//            repository.saveAll(List.of(jan, andrzej));
        };
    }

}
