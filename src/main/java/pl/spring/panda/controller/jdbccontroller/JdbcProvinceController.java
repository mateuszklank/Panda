package pl.spring.panda.controller.jdbccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.spring.panda.model.jdbcmodel.JdbcPatient;
import pl.spring.panda.model.jdbcmodel.JdbcProvince;
import pl.spring.panda.repository.jdbcrepository.JdbcProvinceRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/jdbc")
public class JdbcProvinceController {

    @Autowired
    JdbcProvinceRepository jdbcProvinceRepository;

    @GetMapping("/provinces")
    public ResponseEntity<List<JdbcProvince>> getAllProvinces(@RequestParam(required = false) String provinceName) {
        try {
            List<JdbcProvince> provinces = new ArrayList<JdbcProvince>();

            if (provinceName == null)
                jdbcProvinceRepository.findAll().forEach(provinces::add);
            else
                jdbcProvinceRepository.findByProvinceNameContaining(provinceName).forEach(provinces::add);

            if (provinces.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(provinces, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
