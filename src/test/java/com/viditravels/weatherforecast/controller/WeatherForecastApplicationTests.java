package com.viditravels.weatherforecast.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.viditravels.weatherforecast.entity.LocationDetail;
import com.viditravels.weatherforecast.entity.WeatherDetail;
import com.viditravels.weatherforecast.service.WeatherForecastService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = WeatherForecastController.class)
@WithMockUser
class WeatherForecastApplicationTests  {


  private static final String URI = "/weather";
	
  @Autowired
	private MockMvc mockMvc;

	@MockBean
	 private WeatherForecastService weatherService;
	
	float[] temps= {37.3f,36.8f,36.4f,36.0f, 35.6f,35.3f,35.0f,34.9f,
			 35.8f,38.0f,40.2f, 42.3f,43.8f, 44.9f, 45.5f, 45.7f, 44.9f,  43.0f,  41.7f,   40.8f,  39.9f,39.2f, 38.6f, 38.1f};
	WeatherDetail wd1 = getWeatherDetail(2,Date.valueOf("2022-10-11"),1.254f,1.834f,"Alpharetta","GA",temps);
	{ 
	}
	WeatherDetail wd2 = getWeatherDetail(2,Date.valueOf("2022-10-11"),1.254f,1.834f,"Alpharetta","GA",temps);
	
	WeatherDetail wd3 = getWeatherDetail(3,Date.valueOf("2022-10-13"),1.289f,1.256f,"JohnsCreek","GA",temps);
	
	WeatherDetail mockDataForAdd = getWeatherDetail(4,Date.valueOf("2022-10-14"),1.489f,1.156f,"Roswell","GA",temps);
	
	
	List<WeatherDetail> mockDetailList= new ArrayList<>();
	{
		mockDetailList.add(wd1);
 		mockDetailList.add(wd2);
 		mockDetailList.add(wd3);
	}
	String mockJson = asJsonString(mockDetailList);
	
	@Test
 	@DisplayName("Test Method for GetByDate")
    void testGetWeatherDetailsByDate() throws Exception 
	{
		Mockito.when(weatherService.getDetailByDate(Date.valueOf("2022-10-11"))).thenReturn(mockDetailList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather?date=2022-10-11");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = asJsonString(mockDetailList);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	 	@Test
	 	@DisplayName("Test Method for Get")
	    void testGetWeatherDetails() throws Exception 
	 	{
	 		Mockito.when(weatherService.getAllDetail()).thenReturn(mockDetailList);
	 		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	
	        String expected = asJsonString(mockDetailList);
	        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
			
     }
		
		
		
		@Test
	    void testSaveWeatherDetails() throws Exception 
		{
			Mockito.when(weatherService.saveWeatherDetail(Mockito.any(WeatherDetail.class))).thenReturn(true);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
											.accept(MediaType.APPLICATION_JSON).content(asJsonString(mockDataForAdd))
											.contentType(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		    
		}
		
		@Test
	    void testSaveWeatherDetailsBadRequest() throws Exception {
			Mockito.when(weatherService.saveWeatherDetail(Mockito.any(WeatherDetail.class))).thenReturn(false);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
											.accept(MediaType.APPLICATION_JSON).content(asJsonString(mockDataForAdd))
											.contentType(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

		
			
}
		@Test
	    void testDeleteAllWeatherDetails() throws Exception
	  {
			 RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI);
			 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	         MockHttpServletResponse response = result.getResponse();

	    	 assertEquals(HttpStatus.OK.value(), response.getStatus());
	    }
		
	 static String asJsonString(final Object obj) {
		    try {
		        final ObjectMapper mapper = new ObjectMapper();
		        SimpleModule module = new SimpleModule();
		        module.addSerializer(Date.class, new CustomSerializer());
		        mapper.registerModule(module);
		        
		         String jsonContent = mapper.writeValueAsString(obj);
		        
		        return jsonContent;
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}  
	 static WeatherDetail getWeatherDetail(int id, Date date, float lat, float lon, String city, String state, float[] temps)
	 {
		 WeatherDetail wDetail=new WeatherDetail();
		  wDetail.setId(1); 
		  wDetail.setDate(Date.valueOf("2022-10-11"));
		  LocationDetail ld1= new LocationDetail();
		  ld1.setLat(1.234f);ld1.
		  setLon(1.234f); 
		  ld1.setCity("Atlanta"); 
		  ld1.setState("GA");
		  wDetail.setLocation(ld1); 
		  wDetail.setTemperature(temps);
		  
		  return wDetail;
	 }
	
}
 class CustomSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
            String s = sdf.format(value);
            gen.writeString(s);
          } catch (DateTimeParseException e) {
            System.err.println(e);
            gen.writeString("");
          }     
    }
}