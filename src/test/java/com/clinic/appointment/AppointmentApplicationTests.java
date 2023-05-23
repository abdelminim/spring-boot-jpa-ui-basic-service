package com.clinic.appointment;

import com.clinic.appointment.entity.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentApplicationTests {

	@Test
	void contextLoads() {
	}

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllAppointments() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/STCClinicService/clinic/getAllAppointments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string(containsString("rwe")));
    }

    @Test
    void getCity() throws Exception {
        String expectedJson = new ObjectMapper()
                .writeValueAsString(new City("MyName", 1));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/STCClinicService/cityApi/cities/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().json(expectedJson));
    }

}
