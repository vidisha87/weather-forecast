package com.viditravels.weatherforecast.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viditravels.weatherforecast.entity.LocationDetail;
import com.viditravels.weatherforecast.entity.WeatherDetail;
import com.viditravels.weatherforecast.repository.WeatherForecastRepository;

@Service
public class WeatherForecastService {

	@Autowired 
	 private WeatherForecastRepository weatherRepository;

	
    
    public void deleteAllWeatherDetail() {
    	weatherRepository.deleteAllWeatherDetail();
    }
    
    public List<WeatherDetail> getAllDetail(){
		 List<WeatherDetail> weatherDetailList = weatherRepository.getAllDetail();
	       return weatherDetailList;
	    }
    
    public List<WeatherDetail> getDetailByDate(Date date){
		 List<WeatherDetail> weatherDetailList = weatherRepository.getDetailByDate( date);
	       return weatherDetailList;
	    }
    
    public List<LocationDetail>getLocDetail(){
		 List<LocationDetail> weatherDetailList = weatherRepository.getLocDetail();
				 return weatherDetailList;
    }
   
    public boolean saveWeatherDetail(WeatherDetail detail){
    	boolean isSaved = weatherRepository.saveWeatherDetail(detail);
	       return isSaved;
	    }
   
}
