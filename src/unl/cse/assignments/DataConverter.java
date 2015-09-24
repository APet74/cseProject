package unl.cse.assignments;

/* Phase-I */
import com.airamerica.*;
import com.airamerica.Products.AwardTicket;
import com.airamerica.Products.Products;
import com.airamerica.Products.Ticket;
import com.airamerica.Products.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.airamerica.*;

// Include imports for XML/JSON libraries if needed

import com.thoughtworks.xstream.XStream;



public class DataConverter {

	public static void main(String[] args) {

		//filename string variable
		String fileInput = null;
		
		/*
		 * Bring data into memory
		 */
		
		//Airports
		fileInput = "data/Airports.dat";
		String[] airportsUnparsed = fileReadIn(fileInput);
		
		//Persons
		fileInput = "data/Persons.dat";
		String[] personsUnparsed = fileReadIn(fileInput);
		
		//Customers
		fileInput = "data/Customers.dat";
		String[] customersUnparsed = fileReadIn(fileInput);
		
		//Products
		fileInput = "data/Products.dat";
		String[] productsUnparsed = fileReadIn(fileInput);
		
		/*
		 *	Debugging: Test file input system 
		 * 
		
		System.out.println();
		for (int i = 0; i < airportsUnparsed.length; i++) {
			System.out.println(airportsUnparsed[i]);
		}
		*/
		
		
		/*
		 * Parse out data into object arrays
		 */
		
		//Person
		Person personArray[] = new Person[personsUnparsed.length];
		
		for (int i = 0; i < personsUnparsed.length; i++) {
			//put it into an array of persons
			personArray[i] = parsePerson(personsUnparsed[i]);
		}
		//Customer
		Customer customerArray[] = new Customer[customersUnparsed.length];
		
		for (int i = 0; i < customersUnparsed.length; i++) {
			customerArray[i] = parseCustomer(customersUnparsed[i], personArray);
		}

		//Airports
		Airports airportArray[] = new Airports[airportsUnparsed.length];
		for (int i = 0; i < airportsUnparsed.length; i++) {
			airportArray[i] = parseAirport(airportsUnparsed[i]);
			System.out.println(airportArray[i].getAirportCode());
		}
		
		//Products
		ArrayList <Products> productArray = new ArrayList<Products>();
	
		
		for (int i = 0; i < productsUnparsed.length; i++) {
			productArray.add(parseProduct(productsUnparsed[i], airportArray));
			//System.out.println(productArray[i].getCode());
			
		}
		

		/*
		 * Uncomment the following line to see an example of XML implementation
		 * using XStream
		 */

		//XMLExample();


	}

	
	/*
	 * Method to read in files, place in a string array, and return to main method for
	 * parsing
	 */
	private static String[] fileReadIn(String fileName){
		String[] fileInput;
		
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		fileInput = new String[s.nextInt()];
		fileInput[0] = String.valueOf(fileInput.length);
		s.nextLine();
		
		int i = 0;

		while(s.hasNext()) {
		
			fileInput[i] = s.nextLine();
			i++;
		}
		
		return fileInput;
	}
	
	/*
	 * Method to parse out Address info into an Address object
	 */
	private static Address parseAddress(String unparsed){
		
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
	 * Method to parse out Persons
	 */
	private static Person parsePerson(String unparsed){
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
	 * Method to parse out Customers
	 * Contains potential method to search for previous objects
	 */
	private static Customer parseCustomer(String unparsed, Person [] personArray) {
		String token[] = unparsed.split(";\\s*");
		String customerCode = token[0];
		String customerType = token[1];
		String customerName = token[3];
		int airlineMiles = 0;
		
		//find the person, put into customer object
		Person customerContact = null;
		
		for(int j = 0; j < personArray.length; j++) {
			if (token[2].equals(personArray[j].getPersonCode())){
				customerContact = personArray[j];
				break;
			}
		}
		
		if (token.length > 4){
			airlineMiles = Integer.parseInt(token[4]);
		}
		
		Customer customer = new Customer(customerCode, customerType, customerContact, customerName);
		customer.setAirlineMiles(airlineMiles);
		
		return customer;
	}
	
	private static Airports parseAirport(String unparsed) {
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
		
		Airports thisAirport = new Airports(airportCode, airportName,latDegrees,
				latMinutes,longDegrees,longMinutes,passengerFacilityFee);
		
		
		//find the address, put into address object
		Address address = parseAddress(token[2]);
		thisAirport.setAddress(address);
		
		return thisAirport;
	}
	
	private static Airports findAirport(String airportCode, Airports [] airportsArray){
		Airports airport = null;

			for(int j = 0; j < airportsArray.length; j++) {
				if (airportCode.equals(airportsArray[j].getAirportCode())){
					airport = airportsArray[j];
					break;
				}
			}
		return airport;
		
	}
	
	
	private static Products parseProduct(String unparsed, Airports [] airportArray) {

		String token[] = unparsed.split(";\\s*");
		String productCode = token[0];
		String productType = token[1];
		
		String firstLetter = String.valueOf(productType.charAt(0));
		
		Products parsedProduct = null;

				
		if (productType.equals("T*"));
				Airports depAirportCode = findAirport(token[2], airportArray);
				Airports arrAirportCode = findAirport(token[3], airportArray);
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						arrTime = format.parse(token[5]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
					try {
						arrTime = format.parse(token[5]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					Date seasonEndDate = null;
						try {
							seasonEndDate = season.parse(token[3]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						depAirportCode = findAirport(token[4], airportArray);
						arrAirportCode = findAirport(token[5], airportArray);
						 
					
						try {
							depTime = format.parse(token[6]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							arrTime = format.parse(token[7]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
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
						
						
		
		
		
		/*
		String customerName = token[3];
		int airlineMiles = 0;
		//find the person, put into customer object
		Person customerContact = null;
		
		for(int j = 0; j < personArray.length; j++) {
			if (token[2].equals(personArray[j].getPersonCode())){
				customerContact = personArray[j];
				break;
			}
		}
		
		if (token.length > 4){
			airlineMiles = Integer.parseInt(token[4]);
		}
		
		Customer customer = new Customer(customerCode, customerType, customerContact, customerName);
		customer.setAirlineMiles(airlineMiles);
		*/

		return parsedProduct;
	}
		
	
	
	
	
	
	
	
	/*
	 * An example of using XStream API It exports to "data/Person-example.xml"
	 * NOTE: It may be interesting to note how compositions (and relationships
	 * are exported. NOTE: Pay attention how to alias various properties of an
	 * object.
	 */
	/*
	public static void XMLExample() {
		XStream xstream = new XStream();

		Address address1 = new Address("Street1", "City1", null, null, null);
		Person p1 = new Person("PersonCode1", address1);
		p1.addEmail("Email1");
		p1.addEmail("Email2");
		Person p2 = new Person("PersonCode2", address1);
		p2.addEmail("Email3");
		p2.addEmail("Email4");
		xstream.alias("person", Person.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Person-example.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pw.print("<persons>\n");
		pw.print(xstream.toXML(p1) + "\n");
		pw.print(xstream.toXML(p2) + "\n");
		pw.print("</persons>" + "\n");
		pw.close();

		System.out.println("XML generated at 'data/Person-example.xml'");
	}
	*/
}
