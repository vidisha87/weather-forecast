package com.viditravels.weatherforecast.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viditravels.weatherforecast.entity.WeatherDetail;
import com.viditravels.weatherforecast.service.WeatherForecastService;


@RestController

public class WeatherForecastController {
	
	
	@Autowired
    private WeatherForecastService weatherService;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/weather")
    @ResponseBody
    public  ResponseEntity getWeatherDetails(@RequestParam(value = "date", required = false) Date date){
    	
  
    	List<WeatherDetail>weatherDetailList;
    	if(date!=null)
    		weatherDetailList=weatherService.getDetailByDate(date);
    	else
    		weatherDetailList=weatherService.getAllDetail();
    	if(weatherDetailList!=null && !weatherDetailList.isEmpty())
    	 return  new ResponseEntity<List<WeatherDetail>>(weatherDetailList, HttpStatus.OK);
    	else
    		return ResponseEntity.status(HttpStatus.OK)
                    .body("No Weather data found");
       
    }
    
 
    @PostMapping("/weather")
    @ResponseBody
    public  ResponseEntity saveWeatherDetails(@RequestBody WeatherDetail detail ){
    	
  
    	boolean isSaved;
    	try {
    			isSaved=weatherService.saveWeatherDetail(detail);
    		}
    		catch(Exception ex)
    		{
    			isSaved=false;
    		}
    	if(isSaved)
    			return ResponseEntity.status(HttpStatus.CREATED).body("Data is saved");
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST) .body("Bad Request");
       
    }
    
    @DeleteMapping("/weather")
    @ResponseBody
    public ResponseEntity<String> deleteAllWeatherDetails(){
         weatherService.deleteAllWeatherDetail();
         return ResponseEntity.status(HttpStatus.OK)
                 .body("Deleted all weather detail");

}
}