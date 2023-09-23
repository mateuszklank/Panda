package pl.spring.panda;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.spring.panda.model.Doctor;
import pl.spring.panda.model.Patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoctorControllerTests extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getDoctorsTest() throws Exception {
        String uri = "/api/doctors";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Doctor[] doctors = super.mapFromJson(content, Doctor[].class);
        assertTrue(doctors.length > 0);
    }

    @Test
    public void createDoctor() throws Exception {
        String uri = "/api/doctors";
        Doctor doctor = new Doctor(19L, "Jan", "Testowy", "alergolog");

        String inputJson = super.mapToJson(doctor);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(content, "{\"id\":19,\"first_name\":\"Jan\",\"last_name\":\"Testowy\",\"specialty\":\"alergolog\"}");
    }
}
