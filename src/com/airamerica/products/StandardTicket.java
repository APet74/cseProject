package com.airamerica.products;

import java.util.Date;

import com.airamerica.Airports;

public class StandardTicket extends Ticket {

	
	//these might need to be in a time class 
	//rather than string:
	public StandardTicket(String code, String productType, Airports depAirportCode, Airports arrAirportCode, Date depTime,
			Date arrTime, String flightNo, String flightClass, String aircraftType) {
		super(code, productType, depAirportCode, arrAirportCode, depTime, arrTime, flightNo, flightClass, aircraftType);
	}
	
	
	
	
	

	
 
}
