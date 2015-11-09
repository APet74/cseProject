package unl.cse.assignments;

import java.util.ArrayList;

/* Phase-I */
import com.airamerica.*;
import com.airamerica.dataConversion.FileReadIn;
import com.airamerica.dataConversion.ParseData;
import com.airamerica.dataConversion.XMLOut;
import com.airamerica.interfaces.InvoiceData;
import com.airamerica.invoices.Invoice;
import com.airamerica.invoices.TicketService;
import com.airamerica.products.*;



public class DataConverter {

	public static void main() {


		
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
		
		//Invoices
		fileInput = "data/Invoices.dat";
		String[] invoicesUnparsed = FileReadIn.fileReader(fileInput);
		
		
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
		ArrayList<Person> personArray = new ArrayList<Person>();
		
		for (int i = 0; i < personsUnparsed.length; i++) {
			//put it into an array of persons
			personArray.add(ParseData.parsePerson(personsUnparsed[i]));
		}
		
		//clear data from database
		InvoiceData.removeAllPersons();
		InvoiceData.removeAllCustomers();
		InvoiceData.removeAllAirports();
		InvoiceData.removeAllProducts();
		InvoiceData.removeAllInvoices();
		
		

		//Customer
		ArrayList<Customer> customerArray = new ArrayList<Customer>();
		
		for (int i = 0; i < customersUnparsed.length; i++) {
			customerArray.add(ParseData.parseCustomer(customersUnparsed[i], personArray));
		}
		
		//Airports
		ArrayList<Airport> airportArray = new ArrayList<Airport>();
		for (int i = 0; i < airportsUnparsed.length; i++) {
			airportArray.add(ParseData.parseAirport(airportsUnparsed[i]));
		}
		

		
		//Products
		ArrayList<Product> productArray = new ArrayList<Product>();
		for (int i = 0; i < productsUnparsed.length; i++) {
			productArray.add(ParseData.parseProduct(productsUnparsed[i], airportArray, productArray));		
			//System.out.println(productArray[i].getCode());
		}
		
		
		

		//Invoices
		ArrayList<Invoice> invoiceArray = new ArrayList<Invoice>();
		for (int i = 0; i < invoicesUnparsed.length; i++) {
			invoiceArray.add(ParseData.parseInvoice(invoicesUnparsed[i], customerArray, personArray, productArray));		
			//System.out.println(productArray[i].getCode());
		}
		
		//Add invoice object to Database
		for (Invoice i: invoiceArray){
			/*
			 * String invoiceCode, String customerCode, 
			String salesPersonCode, String invoiceDate
			 */
			InvoiceData.addInvoice(i.getInvoiceCode(), i.getCustomer().getCode(), i.getSalesperson(), i.getSaleDate().toString());
			//add ticketholder info to invoice
			
			//add tickets to invoice
			
			//add services to invoice
			for (TicketService s: i.getServices()){
				
			}
		
		}
		
		/*
		 * Send flat file data to XML
		 */

		
		
		XMLOut.toXML(personArray);
		XMLOut.toXML(customerArray);
		XMLOut.toXML(airportArray);
		XMLOut.toXML(productArray);	
		XMLOut.toXML(invoiceArray);
		
		
		
	}




	

	


	


}