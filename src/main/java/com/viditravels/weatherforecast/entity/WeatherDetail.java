package com.viditravels.weatherforecast.entity;

import lombok.*;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class WeatherDetail {

	private int id;
	private Date date;
    private LocationDetail location;
	//private TemperatureDetail tempDetail;
	private float[] temperature= new float[24];
	
}
