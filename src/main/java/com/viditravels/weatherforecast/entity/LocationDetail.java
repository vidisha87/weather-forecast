package com.viditravels.weatherforecast.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class LocationDetail {

	    @JsonIgnore
	    private int locationId;
	    private float lon;
	    private float lat;
	    private String city;
	    private String state;
	  
}
