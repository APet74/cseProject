package unl.cse.assignments;

/* Phase-I */
import com.airamerica.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
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
