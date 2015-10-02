package com.airamerica.products;

import java.util.Date;

import com.airamerica.Airport;

public class StandardTicket extends Ticket {

	
	//these might need to be in a time class 
	//rather than string:
	public StandardTicket(String code, String productType, Airport depAirportCode, Airport arrAirportCode, Date depTime,
			Date arrTime, String flightNo, String flightClass, String aircraftType) {
		super(code, productType, depAirportCode, arrAirportCode, depTime, arrTime, flightNo, flightClass, aircraftType);
	}
	
	
	
	
	

	
 
}
