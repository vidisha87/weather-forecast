package com.viditravels.weatherforecast.healthcheck;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


 class HealthCheckControllerTest {
	 @Test
	    void testHealthCheck() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HealthCheckController()).build();
	        mockMvc.perform(get("/health-check"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	    }
}
