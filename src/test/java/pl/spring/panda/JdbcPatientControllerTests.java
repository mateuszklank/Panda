package pl.spring.panda;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.spring.panda.model.jdbcmodel.JdbcPatient;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JdbcPatientControllerTests extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getAllPatientsTest() throws Exception {
        String uri = "/api/jdbc/patients";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        JdbcPatient[] patients = super.mapFromJson(content, JdbcPatient[].class);
        assertTrue(patients.length > 0);
    }

    @Test
    public void createPatient() throws Exception {
        String uri = "/api/jdbc/patient";

        JdbcPatient patient = new JdbcPatient(23L, "Mateusz", "Testowy", "m",
                LocalDate.of(1984, 2, 3), "Kielce", 11L, "orzechy",
                180.0, 70.0, "mati@gmail.com");

        String inputJson = super.mapToJson(patient);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals("Patient created JdbcPatient{id=23, first_name='Mateusz', last_name='Testowy', " +
                "gender='m', birth_date=1984-02-03, city='Kielce', province_id=11, allergies='orzechy', height=180.0, " +
                "weight=70.0, email='mati@gmail.com', admissions=null}", content);
    }

    @Test
    public void updatePatient() throws Exception {
        String uri = "/api/jdbc/patient/5";
        JdbcPatient patient = new JdbcPatient();
        patient.setFirst_name("Leonard");
        patient.setLast_name("Gała");
        patient.setGender("m");

        String inputJson = super.mapToJson(patient);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Patient was updated successfully", content);
    }

    @Test
    public void deletePatient() throws Exception {
        String uri = "/api/jdbc/patient/17";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Patient was deleted successfully", content);
    }
}
