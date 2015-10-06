package com.airamerica.dataConversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.airamerica.Address;
import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.invoices.Invoice;
import com.airamerica.products.AwardTicket;
import com.airamerica.products.CheckedBaggage;
import com.airamerica.products.Insurance;
import com.airamerica.products.OffSeasonTicket;
import com.airamerica.products.Product;
import com.airamerica.products.Refreshment;
import com.airamerica.products.Service;
import com.airamerica.products.SpecialAssistance;
import com.airamerica.products.StandardTicket;
import com.airamerica.products.Ticket;

public class ParseData {

	/*
	 * Method to parse out Persons
	 */
	public static Person parsePerson(String unparsed){
		String token[] = unparsed.split(";\\s*");
		String personCode = token[0];
		String nameToken[] = token[1].split(",\\s*");
		String lastName = nameToken[0];
		String firstName = nameToken[1];
		
		//put it into an array of persons
		Person thisPerson = new Person(personCode, firstName, lastName);
		
		//Address
		Address address = parseAddress(token[2]);
		thisPerson.setAddress(address);
		
		
		//Phone number
		if (!token[3].isEmpty()){
			thisPerson.setPhoneNumber(token[3]);
		} else {
			thisPerson.setPhoneNumber("");
		}
		
		//Emails
		if (token.length > 4){
			String emailToken [] = token[4].split(",\\s*");
			
			for (int j = 0; j < emailToken.length; j++) {
				thisPerson.addEmail(emailToken[j]);
			}
		}
		
		return thisPerson;
	}
	
	
	/*
	 * Method to parse out Addresses
	 */
	public static Address parseAddress(String unparsed){
		
		// \\s* ignores any whitespace characters, preventing them from being added
		// to address
		String token[] = unparsed.split(",\\s*");
		
		String street = token[0];
		String city = token[1];
		String state = token[2];
		String zip = token[3];
		String country = token[4];
		
		Address address = new Address(street, city, state, zip, country);
		
		return address;
	}
	
	/*
	 * Method to parse out Customers
	 * Contains potential method to search for previous objects
	 */
	public static Customer parseCustomer(String unparsed, ArrayList<Person> personArray) {
		String token[] = unparsed.split(";\\s*");
		String customerCode = token[0];
		String customerType = token[1];
		String customerName = token[3];
		int airlineMiles = 0;
		
		//find the person, put into customer object
		Person customerContact =  (Person) FindObject.find(token[2], personArray);

		
		if (token.length > 4){
			airlineMiles = Integer.parseInt(token[4]);
		}
		
		Customer customer = new Customer(customerCode, customerType, customerContact, customerName);
		customer.setAirlineMiles(airlineMiles);
		
		return customer;
	}
	
	/*
	 * Method to parse out Airports
	 */
	public static Airport parseAirport(String unparsed) {
		String token[] = unparsed.split(";\\s*");
		String airportCode = token[0];
		String airportName= token[1];
		
		//deal with lat/long
		String lattitudeLongitude[] = token[3].split(",");
		int latDegrees = Integer.parseInt(lattitudeLongitude[0]);
		int latMinutes = Integer.parseInt(lattitudeLongitude[1]);
		int longDegrees = Integer.parseInt(lattitudeLongitude[2]);
		int longMinutes = Integer.parseInt(lattitudeLongitude[3]);
		
		float passengerFacilityFee = Float.parseFloat(token[4]);
		
		Airport thisAirport = new Airport(airportCode, airportName,latDegrees,
				latMinutes,longDegrees,longMinutes,passengerFacilityFee);
		
		
		//find the address, put into address object
		Address address = ParseData.parseAddress(token[2]);
		thisAirport.setAddress(address);
		
		return thisAirport;
	}

	
	/*
	 * Method to parse Products
	 */
	
	
	public static Product parseProduct(String unparsed, ArrayList<Airport> airportArray, ArrayList<Product> productArray) {

		String token[] = unparsed.split(";\\s*");
		String productCode = token[0];
		String productType = token[1];
		
		String firstLetter = String.valueOf(productType.charAt(0));
		
		Product parsedProduct = null;

		switch (firstLetter) {
		
		case ("T") :
			Airport depAirportCode = (Airport) FindObject.find(token[2], airportArray);
			Airport arrAirportCode = (Airport) FindObject.find(token[3], airportArray);
			DateFormat format = new SimpleDateFormat("k:m", Locale.ENGLISH);	
		
			Date depTime = null;
			Date arrTime = null;
				
				
			String flightNo = token[6];
			String flightClass = token[7];
			String aircraftType = token[8];
			
			if (productType.equals("TS")){
				try {
					depTime = format.parse(token[4]);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				try {
					arrTime = format.parse(token[5]);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				parsedProduct = new StandardTicket(productCode, productType, 
						depAirportCode, arrAirportCode, 
						depTime, arrTime, flightNo, flightClass,
						aircraftType);
				
				
			} else if(productType.equals("TA")){
				int pointsPerMile = Integer.parseInt(token[9]);
				try {
					depTime = format.parse(token[4]);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				
		
				try {
					arrTime = format.parse(token[5]);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				
				parsedProduct = new AwardTicket(productCode, productType, 
						depAirportCode, arrAirportCode, 
						depTime, arrTime, flightNo, flightClass,
						aircraftType, pointsPerMile);
			} else if(productType.equals("TO")){
				DateFormat season = new SimpleDateFormat("y-M-d", Locale.ENGLISH);
				
				//Was throwing Exceptions
				Date seasonStartDate = null;
					try {
						seasonStartDate = season.parse(token[2]);
					} catch (ParseException e) {
						
						e.printStackTrace();
					}

				Date seasonEndDate = null;
					try {
						seasonEndDate = season.parse(token[3]);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					depAirportCode = (Airport) FindObject.find(token[4], airportArray);
					arrAirportCode = (Airport) FindObject.find(token[5], airportArray);
					 
				
					try {
						depTime = format.parse(token[6]);
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					
					try {
						arrTime = format.parse(token[7]);
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					
				flightNo = token[8];
				flightClass = token[9];
				aircraftType = token[10];
				float rebate = Float.parseFloat(token[11]);
				
				parsedProduct = new OffSeasonTicket(productCode, productType, 
							depAirportCode, 
							arrAirportCode, depTime, 
							arrTime, flightNo, flightClass, 
							aircraftType, seasonStartDate,
							seasonEndDate, rebate);

				}

			break;
		
		case "S":
					if (productType.equals("SC")) {
						//Search for ticket to make an embedded ticket object
						Ticket ticketCode = (Ticket) FindObject.find(token[2], productArray);
						
						parsedProduct = new CheckedBaggage(productCode,productType,ticketCode);
					} else if (productType.equals("SI")) {
						String insuranceName = token[2];
						String ageClass = token[3];
						float costPerMile = Float.parseFloat(token[4]);
						
						parsedProduct = new Insurance(productCode, productType, insuranceName, ageClass, costPerMile);
					} else if (productType.equals("SS")) {
						String typeOfService = token[2];
						
						parsedProduct = new SpecialAssistance(productCode, productType, typeOfService);
					} else if (productType.equals("SR")) {
						String refreshmentName = token[2];
						float cost = Float.parseFloat(token[3]);
						
						parsedProduct = new Refreshment(productCode, productType, refreshmentName, cost);
					}
				}
	


		return parsedProduct;
	}



	
 

	/*
	 * Note on arraylist structure
	 * This is a take on multidimensional array. Indices of tickets, ticketholder, and ticketservices
	 * all correlate toward the same ticket number. See design document
	 */
	public static Invoice parseInvoice(String unparsed, ArrayList<Customer> customerList,
			ArrayList<Person> personList, ArrayList <Product> productList) {
		String token[] = unparsed.split(";\\s*");
		String invoiceCode = token[0];
		Invoice thisInvoice = new Invoice(invoiceCode);
		
		//customer object
		thisInvoice.setCustomer(token[1], customerList);
		
		// salesperson - person object
		thisInvoice.setSalesperson(token[2]);
		
		// invoice date - date
		DateFormat format = new SimpleDateFormat("y-M-d", Locale.ENGLISH);	
		Date invoiceDate = null;
		
		try {
			invoiceDate = format.parse(token[3]);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		thisInvoice.setSaleDate(invoiceDate);
		

		String productToken[] = token[4].split(",\\s*");
		
		
		
		for (int i = 0; i < productToken.length; i++){
			String productDefined[] = productToken[i].split(":");
			
			if (FindObject.find(productDefined[0], productList) instanceof Ticket) {
				System.out.println(productDefined[0]);
				//Ticket
				thisInvoice.addTicket(productDefined[0]);
				
				//flight date
				Date flightDate = null;
				try {
					flightDate = format.parse(productDefined[1]);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				
				thisInvoice.addFlightDates(flightDate);
				
				
				
			} else if (FindObject.find(productDefined[0], productList) instanceof Service) {
				System.out.println(productDefined[0] + " is a Service");
			}
		}
		
		// tickets - arraylist
		
		// flight dates - arraylist
		
		// ticketholder - arraylist
		
		// services - arraylist of TicketServices
		
	
		return thisInvoice;
	}
}
