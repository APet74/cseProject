package com.airamerica.dataConversion;

import com.airamerica.Airport;
import com.airamerica.Person;
import com.airamerica.products.Product;
import com.airamerica.products.Ticket;

public class FindObject {

	
	public static Airport findAirport(String airportCode, Airport [] airportsArray){
		Airport airport = null;

			for(int j = 0; j < airportsArray.length; j++) {
				if (airportCode.equals(airportsArray[j].getCode())){
					airport = airportsArray[j];
					break;
				}
			}
		return airport;
		
	}
	
	
	public static Ticket findTicket(String productCode, Product[] productArray){
		Ticket product= null;
		String matchCode = null;
		
			for(int j = 0; j < productArray.length; j++) {
				matchCode = productArray[j].getCode();

				if (matchCode.equals(productCode)){
					product = (Ticket) productArray[j];
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
