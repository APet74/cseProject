package unl.cse.assignments;

/* Phase-I */
import com.airamerica.*;
import com.airamerica.Products.*;
import com.airamerica.dataConversion.FileReadIn;
import com.airamerica.dataConversion.ParseData;
import com.airamerica.dataConversion.XMLOut;



public class DataConverter {

	public static void main(String[] args) {

		//filename string variable
		String fileInput = null;
		
		/*
		 * Bring data into memory
		 */
		
		//Airports
		fileInput = "data/Airports.dat";
		new FileReadIn();
		String[] airportsUnparsed = FileReadIn.fileReader(fileInput);
		
		//Persons
		fileInput = "data/Persons.dat";
		String[] personsUnparsed = FileReadIn.fileReader(fileInput);
		
		//Customers
		fileInput = "data/Customers.dat";
		String[] customersUnparsed = FileReadIn.fileReader(fileInput);
		
		//Products
		fileInput = "data/Products.dat";
		String[] productsUnparsed = FileReadIn.fileReader(fileInput);
		
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
			personArray[i] = ParseData.parsePerson(personsUnparsed[i]);
		}
		
		//Customer
		Customer customerArray[] = new Customer[customersUnparsed.length];
		
		for (int i = 0; i < customersUnparsed.length; i++) {
			customerArray[i] = ParseData.parseCustomer(customersUnparsed[i], personArray);
		}
		
		//Airports
		Airports airportArray[] = new Airports[airportsUnparsed.length];
		for (int i = 0; i < airportsUnparsed.length; i++) {
			airportArray[i] = ParseData.parseAirport(airportsUnparsed[i]);
		}
		
		//Products
		Products productArray[] = new Products[productsUnparsed.length];
		for (int i = 0; i < productsUnparsed.length; i++) {
			productArray[i] = (ParseData.parseProduct(productsUnparsed[i], airportArray, productArray));		
			//System.out.println(productArray[i].getCode());
		}

		
		/*
		 * Send flat file data to XML
		 */
		XMLOut.personToXML(personArray);
		XMLOut.customerToXML(customerArray);
		XMLOut.airportToXML(airportArray);
		XMLOut.productToXML(productArray);	
	}

	

	

	


	


}
