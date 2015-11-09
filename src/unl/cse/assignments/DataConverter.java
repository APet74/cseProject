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
		
		
		//From person object to database
		for(Person p: personArray){
			InvoiceData.addPerson(p.getCode(), p.getfirstName(), p.getlastName(), p.getPhoneNumber(), p.getAddressObject().getStreet(),
					p.getAddressObject().getCity(), p.getAddressObject().getState(), p.getAddressObject().getZip(), p.getAddressObject().getCountry());
		}
		//TODO: add emails
		
		
		//Customer
		ArrayList<Customer> customerArray = new ArrayList<Customer>();
		
		for (int i = 0; i < customersUnparsed.length; i++) {
			customerArray.add(ParseData.parseCustomer(customersUnparsed[i], personArray));
		}
		
		//From Customer object to database
		for(Customer c: customerArray){
			InvoiceData.addCustomer(c.getCode(), c.getCustomerType(), c.getPrimaryContact().getCode(), c.getCustomerName(), c.getAirlineMiles());
		}
		
		//Airports
		ArrayList<Airport> airportArray = new ArrayList<Airport>();
		for (int i = 0; i < airportsUnparsed.length; i++) {
			airportArray.add(ParseData.parseAirport(airportsUnparsed[i]));
		}
		
		//From airport object to database
		for(Airport a: airportArray){
			InvoiceData.addAirport(a.getAirportCode(), a.getAirportName(), a.getAddress().getStreet(), a.getAddress().getCity(),
					a.getAddress().getState(), a.getAddress().getZip(), a.getAddress().getCountry(), a.getLattitudeDegrees(), 
					a.getLattitudeMinutes(), a.getLongitudeDegrees(), a.getLongitudeMinutes(), (float)a.getPassengerFee());
		}
		
		//Products
		ArrayList<Product> productArray = new ArrayList<Product>();
		for (int i = 0; i < productsUnparsed.length; i++) {
			productArray.add(ParseData.parseProduct(productsUnparsed[i], airportArray, productArray));		
			//System.out.println(productArray[i].getCode());
		}
		
		//From products object to database
		
		//have to sort between product types
		  //Ticket - Award, OffSeason, Standard
		  //Service - CheckedBaggage, Insurance, Refreshment, SpecialAssistance
		for(Product p: productArray){
			if(p instanceof AwardTicket){
				AwardTicket t = (AwardTicket) p;
				InvoiceData.addAwardsTicket(t.getCode(), t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), 
						t.getDepTime().toString(), t.getArrTime().toString(), t.getFlightNo(), t.getFlightClass(), t.getAircraftType(), 
						t.getPointsPerMile());
				
			} else if (p instanceof OffSeasonTicket){
				OffSeasonTicket t = (OffSeasonTicket) p;
				InvoiceData.addOffSeasonTicket(t.getCode(), t.getSeasonStartDate().toString(), t.getSesaonEndDate().toString(), 
						t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), t.getDepTime().toString(), 
						t.getArrTime().toString(), t.getFlightNo(), t.getFlightClass(), t.getAircraftType(), t.getRebate());
				
			} else if (p instanceof StandardTicket){
				/*
				 * String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType
				 */
				StandardTicket t = (StandardTicket) p;
				InvoiceData.addStandardTicket(t.getCode(), t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), 
						t.getDepTime().toString(), t.getArrTime().toString(), t.getFlightNo(), t.getFlightClass(), t.getAircraftType());
				
			} else if (p instanceof CheckedBaggage){
				/*
				 * String productCode, String ticketCode
				 */
				CheckedBaggage s = (CheckedBaggage) p;
				InvoiceData.addCheckedBaggage(s.getCode(), s.getTicketCode().getCode());
				
			} else if (p instanceof Insurance){
				/*
				 * String productCode, String productName, 
			String ageClass, double costPerMile
				 */
				Insurance s = (Insurance) p;
				InvoiceData.addInsurance(s.getCode(), s.getName(), s.getAgeClass(), (double) s.getCostPerMile());
				
			} else if (p instanceof Refreshment){
				/*
				 * String productCode, String name, double cost
				 */
				Refreshment s = (Refreshment) p;
				InvoiceData.addRefreshment(s.getCode(), s.getName(), (double) s.getCost()); 
				
			} else if (p instanceof SpecialAssistance){
				/*
				 * String productCode, String assistanceType
				 */
				SpecialAssistance s = (SpecialAssistance) p;
				InvoiceData.addSpecialAssistance(s.getCode(), s.getTypeOfService());
				
			}
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