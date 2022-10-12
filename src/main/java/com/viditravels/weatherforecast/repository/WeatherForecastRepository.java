package com.viditravels.weatherforecast.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.viditravels.weatherforecast.entity.LocationDetail;
import com.viditravels.weatherforecast.entity.TemperatureDetail;
import com.viditravels.weatherforecast.entity.WeatherDetail;


@Repository	
public class WeatherForecastRepository {

	 @Autowired
	    JdbcTemplate jdbcTemplate;

	
		class LocationDetailRowMapper implements RowMapper<LocationDetail>{

		        @Override
		        public LocationDetail mapRow(ResultSet resultSet, int i) throws SQLException {
		        	LocationDetail locDetail = new LocationDetail();

		        	locDetail.setLocationId(resultSet.getInt("location_id"));
		        	locDetail.setLon(resultSet.getFloat("lon"));
		        	locDetail.setLat(resultSet.getFloat("lat"));
		        	locDetail.setCity(resultSet.getString("city"));
		        	locDetail.setState(resultSet.getString("state"));
		          
		            return locDetail;
		        }
		    }

		class WeatherDetailRowMapper implements RowMapper<WeatherDetail>{
			@Override
	        public WeatherDetail mapRow(ResultSet resultSet, int i) throws SQLException {
				WeatherDetail weatherDetail = new WeatherDetail();
				
				weatherDetail.setId(resultSet.getInt("id"));
				weatherDetail.setDate(resultSet.getDate("weather_date"));
				
				LocationDetail locDetail= new LocationDetail();
				//locDetail.setLocationId(resultSet.getInt("location_id"));
				locDetail.setLat(resultSet.getFloat("lat"));
				locDetail.setLon(resultSet.getFloat("lon"));
				locDetail.setCity(resultSet.getString("city"));
				locDetail.setState(resultSet.getString("state"));
				weatherDetail.setLocation(locDetail);
				
				TemperatureDetail tempDetail= new TemperatureDetail();
				tempDetail.setTemperature_id(resultSet.getInt("temperature_id"));
				tempDetail.setTemperature1(resultSet.getFloat("temperature_1"));
				tempDetail.setTemperature2(resultSet.getFloat("temperature_2"));
				tempDetail.setTemperature3(resultSet.getFloat("temperature_3"));
				tempDetail.setTemperature4(resultSet.getFloat("temperature_4"));
				tempDetail.setTemperature5(resultSet.getFloat("temperature_5"));
				tempDetail.setTemperature6(resultSet.getFloat("temperature_6"));
				tempDetail.setTemperature7(resultSet.getFloat("temperature_7"));
				tempDetail.setTemperature8(resultSet.getFloat("temperature_8"));
				tempDetail.setTemperature9(resultSet.getFloat("temperature_9"));
				tempDetail.setTemperature10(resultSet.getFloat("temperature_10"));
				tempDetail.setTemperature11(resultSet.getFloat("temperature_11"));
				tempDetail.setTemperature12(resultSet.getFloat("temperature_12"));
				tempDetail.setTemperature13(resultSet.getFloat("temperature_13"));
				tempDetail.setTemperature14(resultSet.getFloat("temperature_14"));
				tempDetail.setTemperature15(resultSet.getFloat("temperature_15"));
				tempDetail.setTemperature16(resultSet.getFloat("temperature_16"));
				tempDetail.setTemperature17(resultSet.getFloat("temperature_17"));
				tempDetail.setTemperature18(resultSet.getFloat("temperature_18"));
				tempDetail.setTemperature19(resultSet.getFloat("temperature_19"));
				tempDetail.setTemperature20(resultSet.getFloat("temperature_20"));
				tempDetail.setTemperature21(resultSet.getFloat("temperature_21"));
				tempDetail.setTemperature22(resultSet.getFloat("temperature_22"));
				tempDetail.setTemperature23(resultSet.getFloat("temperature_23"));
				tempDetail.setTemperature24(resultSet.getFloat("temperature_24"));
				//weatherDetail.setTempDetail(tempDetail);
				float[] temperatures= {tempDetail.getTemperature1(),tempDetail.getTemperature2(),tempDetail.getTemperature3(),tempDetail.getTemperature4(),
						tempDetail.getTemperature5(),tempDetail.getTemperature6(),tempDetail.getTemperature7(),tempDetail.getTemperature8(),
						tempDetail.getTemperature9(),tempDetail.getTemperature10(),tempDetail.getTemperature11(),tempDetail.getTemperature12(),
						tempDetail.getTemperature13(),tempDetail.getTemperature14(),
						tempDetail.getTemperature15(),tempDetail.getTemperature16(),tempDetail.getTemperature17(),tempDetail.getTemperature18(),
						tempDetail.getTemperature19(),tempDetail.getTemperature20(),tempDetail.getTemperature21(),tempDetail.getTemperature22(),
						tempDetail.getTemperature23(),tempDetail.getTemperature24()};
				weatherDetail.setTemperature(temperatures);
	            return weatherDetail;
	        }
		}
		  
		    public void deleteAllWeatherDetail() {
		        String query1 = "DELETE FROM temperature_details";
		      jdbcTemplate.update(query1);
		        String query2 = "DELETE FROM weather_details";
		     jdbcTemplate.update(query2);
		        String query3 = "DELETE FROM location_details";
		       jdbcTemplate.update(query3);
		    }
		    // ordered by id asc
		    public List<LocationDetail> getLocDetail(){
		    	
		        String query = "select * from location_details  order by location_id asc";
		        RowMapper<LocationDetail> rowMapper = new LocationDetailRowMapper();
		        List<LocationDetail> weatherDetailList = new ArrayList<>();
		        weatherDetailList=jdbcTemplate.query(query, rowMapper);
		        return weatherDetailList;
		    }
		    
		    public List<WeatherDetail> getAllDetail(){
		    	String query = "select * from weather_details w, location_details l, temperature_details t where l.location_id=w.location_id and t.weather_id =w.id order by w.id asc";
		        
		        
		        RowMapper<WeatherDetail> rowMapper = new WeatherDetailRowMapper();
		        List<WeatherDetail> weatherDetailList = new ArrayList<>();
		        weatherDetailList=jdbcTemplate.query(query, rowMapper);
		        
		      
		        return weatherDetailList;
		    }
		    public List<WeatherDetail> getDetailByDate(Date date){
		    	
		        String query = "select * from weather_details w, location_details l, temperature_details t where l.location_id=w.location_id and t.weather_id =w.id and "
		        		+ " w.weather_date = ? order by w.id asc";
		        
		        
		        RowMapper<WeatherDetail> rowMapper = new WeatherDetailRowMapper();
		        List<WeatherDetail> weatherDetailList = new ArrayList<>();
		        weatherDetailList=jdbcTemplate.query(query, rowMapper, date);
		        
		      	
		        return weatherDetailList;
		    }
		    public boolean saveWeatherDetail(WeatherDetail detail)
		    {
		    	
		    	boolean isSaved=false;
		    	 SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		         jdbcInsert.withTableName("location_details").usingGeneratedKeyColumns("Primary_key");
		         Map<String, Object> parametersLoc = new HashMap<>();
		         parametersLoc.put("lon", detail.getLocation().getLon());
		         parametersLoc.put("lat", detail.getLocation().getLat());
		         parametersLoc.put("city", detail.getLocation().getCity());
		         parametersLoc.put("state", detail.getLocation().getState());
		         // execute insert
		         Number keyLoc = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource( parametersLoc));
		         if(keyLoc!=null)
		         {
		      
				    try
				    {
				      String query = "insert into weather_details(id,weather_date,location_id) values(?,?,?)";
				      int rowsInsertedWeather=  jdbcTemplate.update(query,detail.getId(),detail.getDate(), keyLoc.intValue());
				    
				         if(rowsInsertedWeather==1)
				         {  String query1 = "insert into temperature_details ( weather_id,temperature_1,temperature_2, temperature_3, temperature_4,temperature_5,temperature_6,temperature_7,temperature_8,temperature_9,\n" + 
				      		"temperature_10,temperature_11,temperature_12,temperature_13,temperature_14,temperature_15,temperature_16,temperature_17,temperature_18,temperature_19,temperature_20,temperature_21,\n" + 
				      		"temperature_22,temperature_23,temperature_24) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				         	int rowsInsertedTemp=  jdbcTemplate.update(query1,detail.getId(),detail.getTemperature()[0], detail.getTemperature()[1],detail.getTemperature()[2],
				    		  detail.getTemperature()[3],detail.getTemperature()[4],detail.getTemperature()[5],detail.getTemperature()[6],detail.getTemperature()[7],
				    		  detail.getTemperature()[8],detail.getTemperature()[9],detail.getTemperature()[10],detail.getTemperature()[11],detail.getTemperature()[12],
				    		  detail.getTemperature()[13],detail.getTemperature()[14],detail.getTemperature()[15],detail.getTemperature()[16],
				    		  detail.getTemperature()[17],detail.getTemperature()[18],detail.getTemperature()[19],detail.getTemperature()[20],detail.getTemperature()[21],
				    		  detail.getTemperature()[22],detail.getTemperature()[23]);
				    		  
				         
				         	if(rowsInsertedTemp==1)
				        	 	isSaved=true; 
				         }
				         
				    }
				    /*catch(DuplicateKeyException ex)
				    {
				    	if(ex.getMessage().contains("weather_details.PRIMARY"))
				    		isSaved=false;
				    }*/catch(Exception e)
				    {
				    	  System.out.println("Exception in Repository::"+e.getMessage());
				    		isSaved=false;
				    }
		         }
		        
		    	 
		    	 
		        return isSaved;
		    }
	
}
