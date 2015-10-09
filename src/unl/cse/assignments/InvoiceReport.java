package unl.cse.assignments;

import java.util.ArrayList;

import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.dataConversion.FileReadIn;
import com.airamerica.dataConversion.FindObject;
import com.airamerica.dataConversion.ParseData;
import com.airamerica.invoices.Invoice;
import com.airamerica.products.AwardTicket;
import com.airamerica.products.Insurance;
import com.airamerica.products.OffSeasonTicket;
import com.airamerica.products.Product;
import com.airamerica.products.Service;
import com.airamerica.products.StandardTicket;
import com.airamerica.products.Ticket;
<<<<<<< HEAD
import com.thoughtworks.xstream.io.binary.Token.Formatter;
=======
import com.airamerica.utils.StandardUtils;
>>>>>>> JohnInvoiceReport

import java.lang.StringBuilder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;


/* Assignment 3,5 and 6 (Project Phase-II,IV and V) */

public class InvoiceReport  {
	
	private String generateSummaryReport() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Executive Summary Report\n");
		sb.append("=========================\n");
		
		//TODO: Add code for generating summary of all Invoices
		
		return sb.toString();
	}
	

	private String getTravelSummary(ArrayList<Invoice> invoiceArray, int index, ArrayList<Product> productArray) {
		StringBuilder sb = new StringBuilder();
		sb.append("FLIGHT INFORMATION\n");
		
		DateFormat formatter = new SimpleDateFormat("h:ma");
		
		//header
		sb.append(String.format("%-15s%-10s%-7s%-30s%-30s%s\n", "Day, Date", "Flight", "Class",
				"Departure City and Time", "Arrival City and Time", "Aircraft"));
		
		//loop through tickets
		for (int j = 0; j < invoiceArray.get(index).getTicketCodesSize(); j++){
			
			//date
			sb.append(String.format("%ta, %td%tb%-5ty%-10s%-7s%-30s%-30s%s\n%-32s%-30s%-30s\n", invoiceArray.get(j).getFlightDates(j),
					invoiceArray.get(j).getFlightDates(j), invoiceArray.get(j).getFlightDates(j), 
					invoiceArray.get(j).getFlightDates(j),
					//flight
					((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getFlightNo(),
					//Class
					((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getFlightClass(),
					//departing/arriving cities and states
					((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getCityState("departing"),
					((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getCityState("arriving"),
					//aircraft type
					((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getAircraftType(),
					//open space
					"",
					//departing airport code and time
					"(" + ((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getAirportCode("departing") +
					") " + formatter.format(((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getFlightTime("departing")),
					//arriving airport code and time
					"(" + ((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getAirportCode("arriving") +
					") " + formatter.format(((Ticket) (FindObject.find(invoiceArray.get(j).getTicketCodes(j), productArray))).getFlightTime("arriving"))));
			


			sb.append(String.format("    *%s\n", invoiceArray.get(j).getComment(j)));

		}
		
		
		/*
		if(c.getCustomer().getCustomerType().equals("C")){
			sb.append("[Corporate]\n");
		}else if(c.getCustomer().getCustomerType().equals("V")){
			sb.append("[Government]\n");
		}else{
			sb.append("[General]\n");
		}
		sb.append(c.getCustomer().getPrimaryContact().getlastName() + ", " + c.getCustomer().getPrimaryContact().getfirstName() + "\n");
		String customerAddress[] = c.getCustomer().getPrimaryContact().getAddress().split(",\\s*");
		sb.append(customerAddress[0] + "\n" + customerAddress[1] + " " + customerAddress[2] + " " + customerAddress[3] + " " + customerAddress[4] + "\n");
		if(c.getSalesperson().equals("online")){
			sb.append("Salesperson: ONLINE, null\n");
		}else{
		Person customerContact =  (Person) FindObject.find(c.getSalesperson(), personArray);
			sb.append("Salesperson: " + customerContact.getlastName() + ", " + customerContact.getfirstName() + "\n");
		}
		*/
		
		sb.append("\n");
		return sb.toString();
		
	}
	
	private String getCostSummary(ArrayList<Invoice> invoiceArray,int i, ArrayList<Person> personArray, ArrayList<Product> productArray) {
		StringBuilder sb = new StringBuilder();
		sb.append("CUSTOMER INFORMATION:\n");
		sb.append("==================================================\n");
		Invoice c = invoiceArray.get(i);
		
		
		sb.append(c.getCustomer().getCustomerName() + " " + c.getCustomer().getCode() + "\n");
		if(c.getCustomer().getCustomerType().equals("C")){
			sb.append("[Corporate]\n");
		}else if(c.getCustomer().getCustomerType().equals("V")){
			sb.append("[Government]\n");
		}else{
			sb.append("[General]\n");
		}
		sb.append(c.getCustomer().getPrimaryContact().getlastName() + ", " + c.getCustomer().getPrimaryContact().getfirstName() + "\n");
		String customerAddress[] = c.getCustomer().getPrimaryContact().getAddress().split(",\\s*");
		sb.append(customerAddress[0] + "\n" + customerAddress[1] + " " + customerAddress[2] + " " + customerAddress[3] + " " + customerAddress[4] + "\n");
		if(c.getSalesperson().equals("online")){
			sb.append("Salesperson: ONLINE, null\n");
		}else{
		Person customerContact =  (Person) FindObject.find(c.getSalesperson(), personArray);
			sb.append("Salesperson: " + customerContact.getlastName() + ", " + customerContact.getfirstName() + "\n");
		}
		
		sb.append("\n\nFARES AND SERVICES\n");
		sb.append("==================================================\n");
		sb.append("Code\tItem\t\t\t\t\t\t\t\t\t\tSubtotal\tTax\t\tTotal\n");
		double totalTax = 0;
		for(int j = 0; j < c.getTicketCodesSize(); j++){
			Product ticketObject =  (Product) FindObject.find(c.getTicketCodes(j), productArray);
			Ticket ticketObj = (Ticket) ticketObject;
			//sb.append(ticketObj.getCode());
			if(ticketObject.getProductType().equals("TS")){
				ticketObj = (StandardTicket) ticketObject;
				Airport a1 = ticketObj.getArrAirportCode();
				Airport a2 = ticketObj.getDepAirportCode();
				NumberFormat formatter = new DecimalFormat("#0.00");
				ticketObj.getFees();
				double fee = ticketObj.getFees() * c.getTicketHolder().get(j).getPerson().size();
				 double tax = ticketObj.getTax(fee) + (4 * c.getTicketHolder().get(j).getPerson().size()) + (5.6 * c.getTicketHolder().get(j).getPerson().size() + (a2.getPassengerFee() * c.getTicketHolder().get(j).getPerson().size()));
					totalTax = totalTax + tax;
					double total = fee + tax;
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",ticketObj.getCode(), "Standard Ticket(" + ticketObj.getFlightClass() + ") " + a2.getCode() + " to " + a1.getCode() + "(" + formatter.format(ticketObj.getMiles(a1, a2)) + " miles)", "$", fee, " $", tax, " $", total));
				sb.append(String.format("%-8s%-71s\n", "", "(" + c.getTicketHolder().get(j).getPerson().size() + " units @ " + formatter.format(ticketObj.getFees()) + "/unit)"));
			}else if(ticketObject.getProductType().equals("TO")){
				
				if(ticketObj.getSeasonStartDate().compareTo(ticketObject) * d.compareTo(ticketObj.getSesaonEndDAte()) > 0;)
				 ticketObj = (OffSeasonTicket) ticketObject;
				sb.append("\tOffseason Ticket");
			}else{
				 ticketObj = (AwardTicket) ticketObject;
				sb.append("\tAward Ticket");
			}
		
		
		
		}
		for(int k = 0; k < c.getServices().size(); k++){
			NumberFormat formatter = new DecimalFormat("#0.00");
			Product serviceObject = (Product) FindObject.find(c.getServices().get(k).getServiceCode(), productArray);
			Service serviceObj = (Service) serviceObject;
			

			
			if(serviceObject.getProductType().equals("SC")){
				double price = serviceObj.getCostPerMile();
				price = serviceObj.getFees(c.getServices().get(k).getUnits());
				double tax = price * .04;
				double total = price + tax;
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n", serviceObj.getCode(), "Baggage (" + c.getServices().get(k).getUnits() + " @ $25.00 for 1st and $35.00 onwards)", "$", price, " $", tax, " $", total));
			}else if(serviceObject.getProductType().equals("SI")){  
				Ticket ticketObj = (Ticket) FindObject.find(c.getServices().get(k).getTicketCode(), productArray);
				
				Airport a1 = ticketObj.getArrAirportCode();
				Airport a2 = ticketObj.getDepAirportCode();
				
				serviceObj = (Insurance) serviceObj;
				double price = serviceObj.getCostPerMile();
				price = (price * ticketObj.getMiles(a1, a2)) * c.getServices().get(k).getUnits();
				double tax = price * .04;
				double total = price + tax;
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",serviceObj.getCode(), "Insurance" + " " + serviceObj.getName() + " (" + serviceObj.getAgeClass() + ")", "$", price, " $", tax, " $", total));
				sb.append(String.format("%-8s%-71s\n", "", "(" + c.getServices().get(k).getUnits() + " units @ " + serviceObj.getCostPerMile() + " perMile x " + formatter.format(ticketObj.getMiles(a1, a2)) + " miles)"));
			}else if(serviceObject.getProductType().equals("SS")){
				Person personObj = (Person) FindObject.find(c.getServices().get(k).getPersonCode(), personArray);
				String name = personObj.getlastName() + "," + personObj.getfirstName();
 				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",serviceObj.getCode(), "Special Assistance for" + "[" + name  + "]"  + " (" +serviceObj.getName() +")", "$", 0.00, " $", 0.00, " $", 0.00));
			}else{
				double price = serviceObj.getFees();
				price = price - (price * .05);
				double tax = serviceObj.getTax();
				double total = price + tax;
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n", serviceObj.getCode(), serviceObj.getName() + "(" + c.getServices().size() + " @ $" + formatter.format(serviceObj.getFees()) + "/unit with 5% off)","$", price, " $", tax, " $", total ));
			}
			
		}
		
		return sb.toString();
		
	}

	public String getInvoiceHeader (ArrayList<Invoice> invoiceArray, int index) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\nInvoice %s\n", invoiceArray.get(index).getInvoiceCode()));

		sb.append(String.format("----------------------------------------------------------------------------------------------------------------\n"));

		sb.append(String.format("%-87sPNR", "AIR AMERICA"));
				
		sb.append(String.format("%s%tb %td, %-65tY%s\n", "\nIssueDate: ", invoiceArray.get(index).getSaleDate(),invoiceArray.get(index).getSaleDate(),invoiceArray.get(index).getSaleDate(), 
				StandardUtils.generatePNR()));

		sb.append(String.format("----------------------------------------------------------------------------------------------------------------\n"));
		
		return sb.toString();
	}
	
	public String generateDetailReport(ArrayList<Invoice> invoiceArray, ArrayList<Person> personArray, ArrayList<Product> productArray) {
	StringBuilder sb = new StringBuilder();		
	sb.append("Individual Invoice Detail Reports\n");
	sb.append("==================================================");
	
	/* TODO: Loop through all invoices and call the getTravelSummary() and 
	getCostSummary() for each invoice*/
	for(int i = 0; i < invoiceArray.size(); i++){
		
		String invoiceHeader = getInvoiceHeader(invoiceArray, i);
		String flightSummary = getTravelSummary(invoiceArray, i, productArray);
		String costSummary = getCostSummary(invoiceArray, i, personArray, productArray);
		
		
		sb.append(invoiceHeader);
		sb.append(flightSummary);
		sb.append(costSummary);
	}
	
	
	return sb.toString();
	}

	public static void main(String args[]) {

	    
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
		
		
		//Person
		ArrayList<Person> personArray = new ArrayList<Person>();
		
		for (int i = 0; i < personsUnparsed.length; i++) {
			//put it into an array of persons
			personArray.add(ParseData.parsePerson(personsUnparsed[i]));
		}
		
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
		
		
		//DataConverter.main(); //calls our DataConverter so that it runs and parses all the files.
		InvoiceReport ir = new InvoiceReport();
		String summary = ir.generateSummaryReport();
		String details = ir.generateDetailReport(invoiceArray, personArray, productArray);
		
		System.out.println(summary);
		System.out.println("\n\n");
		System.out.println(details);
		
		System.out.println("======================================================================================================================");
		System.out.println("\n\n");
		
	}
}
