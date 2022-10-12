package com.viditravels.weatherforecast.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viditravels.weatherforecast.entity.LocationDetail;
import com.viditravels.weatherforecast.entity.WeatherDetail;

@SpringBootTest
class WeatherForecastApplicationTests {
/*
	@Test
	void contextLoads() {
		
	}
	 @Test
	    void testGetWeatherDetails() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WeatherForecastController()).build();
	        mockMvc.perform(get("/weather"))
	                .andExpect(status().isOk());
	    }
	 @Test
	    void testGetWeatherDetailsByDate() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WeatherForecastController()).build();
	        mockMvc.perform(get("/weather?date='1999-10-10'"))
	                .andExpect(status().isOk());
	    }
	 
	
	 
	
	 @Test
	    void testSaveWeatherDetails() throws Exception {
		 
		 WeatherDetail weatherObj = new WeatherDetail();
			// setting fields for the NewObject  
		 weatherObj.setId(1);
		 weatherObj.setDate(Date.valueOf("1999-10-11"));
		 LocationDetail locObj=new LocationDetail();
		 locObj.setLon(1.234f);
		 locObj.setLat(1.234f);
		 locObj.setCity("Atlanta");
		 locObj.setCity("GA");
		 weatherObj.setLocation(locObj);
		 float[] tempArr= { 37.3f,36.8f,36.4f,36.0f, 35.6f,35.3f,35.0f,34.9f,
				 35.8f,38.0f,40.2f, 42.3f,43.8f, 44.9f, 45.5f, 45.7f, 44.9f,  43.0f,  41.7f,   40.8f,  39.9f,39.2f, 38.6f, 38.1f};
		weatherObj.setTemperature(tempArr);
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WeatherForecastController()).build();
	       
	        mockMvc.perform(post("/weather")
	  			  .content(asJsonString(weatherObj))
	  			  .contentType(MediaType.APPLICATION_JSON)
	  			  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	        
	        mockMvc.perform(post("/weather")
		  			  .content(asJsonString(weatherObj))
		  			  .contentType(MediaType.APPLICATION_JSON)
		  			  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	    }
	 
	  static String asJsonString(final Object obj) {
		    try {
		        final ObjectMapper mapper = new ObjectMapper();
		        final String jsonContent = mapper.writeValueAsString(obj);
		        return jsonContent;
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}  
	  @Test
	    void testDeleteAllWeatherDetails() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WeatherForecastController()).build();
	        mockMvc.perform(delete("/weather"))
	                .andExpect(status().isOk()) ;
	    }*/
}
