package com.airamerica.dataConversion;

import com.airamerica.Airports;
import com.airamerica.Person;
import com.airamerica.Products.Products;
import com.airamerica.Products.Tickets;

public class FindObject {
	
	public static Airports findAirport(String airportCode, Airports [] airportsArray){
		Airports airport = null;

			for(int j = 0; j < airportsArray.length; j++) {
				if (airportCode.equals(airportsArray[j].getAirportCode())){
					airport = airportsArray[j];
					break;
				}
			}
		return airport;
		
	}
	
	
	public static Tickets findTicket(String productCode, Products[] productArray){
		Tickets product= null;
		String matchCode = null;
		
			for(int j = 0; j < productArray.length; j++) {
				matchCode = productArray[j].getCode();

				if (matchCode.equals(productCode)){
					product = (Tickets) productArray[j];
					break;
				}
			}
		return product;
		
	}
	
	public static Person findPerson(String personCode, Person[] personArray){
		Person person= null;
		String matchCode = null;
		
			for(int j = 0; j < personArray.length; j++) {
				matchCode = personArray[j].getPersonCode();

				if (matchCode.equals(personCode)){
					person = personArray[j];
					break;
				}
			}
		return person;
		
	}
}
