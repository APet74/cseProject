package unl.cse.assignments;

import java.util.ArrayList;

import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.dataConversion.FileReadIn;
import com.airamerica.dataConversion.FindObject;
import com.airamerica.dataConversion.ParseData;
import com.airamerica.dataConversion.XMLOut;
import com.airamerica.invoices.Invoice;
import com.airamerica.products.AwardTicket;
import com.airamerica.products.Insurance;
import com.airamerica.products.OffSeasonTicket;
import com.airamerica.products.Product;
import com.airamerica.products.Service;
import com.airamerica.products.StandardTicket;
import com.airamerica.products.Ticket;

import com.thoughtworks.xstream.io.binary.Token.Formatter;

import com.airamerica.utils.StandardUtils;


import java.lang.StringBuilder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;


/* Assignment 3,5 and 6 (Project Phase-II,IV and V) */

public class InvoiceReport  {
	
	
	
	private String generateSummaryReport(ArrayList<String> invoiceNum, ArrayList<String> customerName, ArrayList<String> salesPerson, double[] subTotalEx, double[] feesEx, double[] taxesEx, double[] discountEx, double[] totalEx) {
		StringBuilder sb = new StringBuilder();
		/*
		 * Lines 43-47 create the total objects to sum the sub totals, fees, taxes, discounts and total cost.
		 */
		double sub = 0;
		double tax = 0;
		double fee = 0;
		double discount = 0;
		double total = 0;
		sb.append("Executive Summary Report\n");
		sb.append("=========================\n");
		//creates the heading
		sb.append(String.format("%-10s%-50s%-20s%-2s%-8s%-2s%-8s%-2s%-8s%-2s%-8s%-2s%-8s\n", "Invoice","Customer","Salesperson","","Subtotal", "", "Fees", "", "Taxes", "", "Discount", "", "total" ));
		/*
		* runs through a loop for each invoice, invoiceNum is a holder of all the invoiceNumbers, if there are 4 invoices then there should be four numbers and
		* that means for numbers stored into this ArrayList, we could have used any of the different arrays/ArrayLists to get the size of the loop but we choose invoiceNum simply because it came first
		*/
		for(int i = 0; i < invoiceNum.size(); i++){
			//following two lines are formatters to one, get two decimal places and then add the negative sign to the discount which means we have to make it a string.
			NumberFormat formatter = new DecimalFormat("#0.00");
			String discountNeg = "-" + String.valueOf(formatter.format(discountEx[i]));
			//string builder to build the output
			sb.append(String.format("%-10s%-50s%-20s%-2s%-8.2f%-2s%-8.2f%-2s%-8.2f%-2s%-8s%-2s%-8.2f\n", invoiceNum.get(i),customerName.get(i), salesPerson.get(i),"$",subTotalEx[i], "$", feesEx[i], "$", taxesEx[i], "$", discountNeg, "$", totalEx[i]));
			/*
			 * Following lines 65 through 69 add the totals for sum of each
			 */
			sub = subTotalEx[i] + sub;
			tax = taxesEx[i] + tax;
			fee = feesEx[i] + fee;
			discount = discountEx[i] + discount;
			total = totalEx[i] + total;
		} 
		//againt using the formatting for adding a negative sign in front of discount
		NumberFormat formatter = new DecimalFormat("#0.00");
		String discountNeg = "-" + String.valueOf(formatter.format(discount));
		//output
		sb.append(String.format("%-130s\n", "=================================================================================================================================="));
		sb.append(String.format("%-10s%-50s%-20s%-2s%-8.2f%-2s%-8.2f%-2s%-8.2f%-2s%-8s%-2s%-8.2f\n", "","" , "","$", sub, "$", fee, "$", tax, "$", discountNeg, "$", total));
		
		
		
		return sb.toString();
	}
	

	private String getTravelSummary(ArrayList<Invoice> invoiceArray, int index, ArrayList<Product> productArray, ArrayList<Person> personArray) {
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
			
			sb.append(String.format("%-8s%-20s%-8s%s\n", "","Traveller","Age","SeatNo"));


			//invoiceArray.get(index).getTicketHolder().get(j).getAge().size()
			for(int k = 0; k < invoiceArray.get(index).getTicketHolder().get(j).getNumberOfPassengers(); k++){

			sb.append(String.format("%-8s%-20s%-8s%s\n",
					
					//name
					"",
					invoiceArray.get(index).getTicketHolder().get(j).getName(k, personArray),
					//age
					invoiceArray.get(index).getTicketHolder().get(j).getAgeOfTicketHolder(k),
					//seat number
					invoiceArray.get(index).getTicketHolder().get(j).getSeatOfTicketholder(k)
					));
			
			}

			sb.append(String.format("    *%s\n", invoiceArray.get(index).getComment(j)));

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
	
private String getCostSummary(ArrayList<Invoice> invoiceArray,int i, ArrayList<Person> personArray, ArrayList<Product> productArray, ArrayList<Customer> customerArray, ArrayList<String> invoiceNum, ArrayList<String> customerName, ArrayList<String> salesPerson, double[] subTotalEx, double[] feesEx, double[] taxesEx,double[] discountEx,double[] totalEx) {
		
		
		StringBuilder sb = new StringBuilder();
		//output
		sb.append("CUSTOMER INFORMATION:\n");
		sb.append("==================================================\n");
		//makes invoice our object to be used throughout the method, invoiceArray is passed and invoice is set to be the the current index of the ArrayList invoiceArray
		Invoice invoice = invoiceArray.get(i);
		
		//adds the invoice code to the invoiceNum array
		invoiceNum.add(invoiceArray.get(i).getInvoiceCode());
		
		
		sb.append(invoice.getCustomer().getCustomerName() + " " + invoice.getCustomer().getCode() + "\n");
		if(invoice.getCustomer().getCustomerType().equals("C")){
			customerName.add(invoice.getCustomer().getCustomerName() + "[Corporate]");
			sb.append("[Corporate]\n");
		}else if(invoice.getCustomer().getCustomerType().equals("V")){
			sb.append("[Government]\n");
			customerName.add(invoice.getCustomer().getCustomerName() + "[Government]");
		}else{
			sb.append("[General]\n");
			customerName.add(invoice.getCustomer().getCustomerName() + "[General]");
		}
		sb.append(invoice.getCustomer().getPrimaryContact().getlastName() + ", " + invoice.getCustomer().getPrimaryContact().getfirstName() + "\n");
		String customerAddress[] = invoice.getCustomer().getPrimaryContact().getAddress().split(",\\s*");
		sb.append(customerAddress[0] + "\n" + customerAddress[1] + " " + customerAddress[2] + " " + customerAddress[3] + " " + customerAddress[4] + "\n");
		if(invoice.getSalesperson().equals("online")){
			sb.append("Salesperson: ONLINE, null\n");
			salesPerson.add("ONLINE, null");
		}else{
		Person customerContact =  (Person) FindObject.find(invoice.getSalesperson(), personArray);
		
			sb.append("Salesperson: " + customerContact.getlastName() + ", " + customerContact.getfirstName() + "\n");
			salesPerson.add(customerContact.getlastName()+ ", " + customerContact.getfirstName());
		}
		
		sb.append("\n\nFARES AND SERVICES\n");
		sb.append("==================================================\n");
		sb.append(String.format("%-8s%-71s%10s%10s%10s\n", "Code", "Item", "Subtotal", "Tax", "Total"));
		double totalTax = 0;
		double totalSub = 0;
		double totalTotal = 0;
		for(int j = 0; j < invoice.getTicketCodesSize(); j++){
			Product ticketObject =  (Product) FindObject.find(invoice.getTicketCodes(j), productArray);
			Ticket ticketObj = (Ticket) ticketObject;
			//sb.append(ticketObj.getCode());
			if(ticketObject.getProductType().equals("TS")){
				ticketObj = (StandardTicket) ticketObject;
				Airport a1 = ticketObj.getArrAirportCode();
				Airport a2 = ticketObj.getDepAirportCode();
				NumberFormat formatter = new DecimalFormat("#0.00");
				ticketObj.getFees();
				double fee = ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size();
				 double tax = ticketObj.getTax(fee) + (4 * invoice.getTicketHolder().get(j).getPerson().size()) + (5.6 * invoice.getTicketHolder().get(j).getPerson().size() + (a1.getPassengerFee() * invoice.getTicketHolder().get(j).getPerson().size()));
					totalTax = totalTax + tax;
					double total = fee + tax;
					totalSub = totalSub + fee;
					totalTotal = totalTotal + total;
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",ticketObj.getCode(), "Standard Ticket(" + ticketObj.getFlightClass() + ") " + a2.getCode() + " to " + a1.getCode() + "(" + formatter.format(ticketObj.getMiles(a1, a2)) + " miles)", "$", fee, " $", tax, " $", total));
				sb.append(String.format("%-8s%-71s\n", "", "(" + invoice.getTicketHolder().get(j).getPerson().size() + " units @ " + formatter.format(ticketObj.getFees()) + "/unit)"));
			}else if(ticketObject.getProductType().equals("TO")){
				Airport a1 = ticketObj.getArrAirportCode();
				Airport a2 = ticketObj.getDepAirportCode();
				NumberFormat formatter = new DecimalFormat("#0.00");
				if(ticketObj.getSeasonStartDate().compareTo(invoice.getFlightDates(j)) * invoice.getFlightDates(j).compareTo(ticketObj.getSesaonEndDAte()) > 0){
					ticketObj.getFees();
					double fee = (ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size()) - ((ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size()) * ticketObj.getRebate()) + 20 ;
					 double tax = ticketObj.getTax(fee) + (4 * invoice.getTicketHolder().get(j).getPerson().size()) + (5.6 * invoice.getTicketHolder().get(j).getPerson().size() + (a1.getPassengerFee() * invoice.getTicketHolder().get(j).getPerson().size()));
						totalTax = totalTax + tax;
						double total = fee + tax;
						double percentOff = ticketObj.getRebate() * 100;
						totalSub = totalSub + fee;
						totalTotal = totalTotal + total;
					sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",ticketObj.getCode(), "OffSeason Ticket(" + ticketObj.getFlightClass() + ") " + a2.getCode() + " to " + a1.getCode() + "(" + formatter.format(ticketObj.getMiles(a1, a2)) + " miles) %" + formatter.format(percentOff) , "$", fee, " $", tax, " $", total));
					sb.append(String.format("%-8s%-71s\n", "", "(" + invoice.getTicketHolder().get(j).getPerson().size() + " units @ " + formatter.format(ticketObj.getFees()) + "/unit with $20.00 fee) "));
				}else{
					ticketObj.getFees();
					double fee = (ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size()) + 20 ;
					 double tax = ticketObj.getTax(fee) + (4 * invoice.getTicketHolder().get(j).getPerson().size()) + (5.6 * invoice.getTicketHolder().get(j).getPerson().size() + (a1.getPassengerFee() * invoice.getTicketHolder().get(j).getPerson().size()));
						totalTax = totalTax + tax;
						double total = fee + tax;
						double percentOff = ticketObj.getRebate() * 100;
						totalSub = totalSub + fee;
						totalTotal = totalTotal + total;
					sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",ticketObj.getCode(), "OffSeason Ticket(" + ticketObj.getFlightClass() + ") " + a2.getCode() + " to " + a1.getCode() + "(" + formatter.format(ticketObj.getMiles(a1, a2)) + " miles) %0.00" , "$", fee, " $", tax, " $", total));
					sb.append(String.format("%-8s%-71s\n", "", "(" + invoice.getTicketHolder().get(j).getPerson().size() + " units @ " + formatter.format(ticketObj.getFees()) + "/unit with $20.00 fee) "));
				}
				
			}else{
				Airport a1 = ticketObj.getArrAirportCode();
				Airport a2 = ticketObj.getDepAirportCode();
				NumberFormat formatter = new DecimalFormat("#0.00");
				ticketObj.getFees();
				double fee = ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size();
				 double tax = ticketObj.getTax(30) + (4 * invoice.getTicketHolder().get(j).getPerson().size()) + (5.6 * invoice.getTicketHolder().get(j).getPerson().size() + (a1.getPassengerFee() * invoice.getTicketHolder().get(j).getPerson().size()));
					totalTax = totalTax + tax;
					double total = 30 + tax;
					totalSub = totalSub + 30;
					totalTotal = totalTotal + total;
					Customer customObj = (Customer) FindObject.find(invoice.getCustomer().getCode(), customerArray);
					int rewardMiles = customObj.getAirlineMiles();
					int milesLeft = rewardMiles - (int) (ticketObj.getMiles(a1, a2) * invoice.getTicketHolder().get(j).getPerson().size());
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",ticketObj.getCode(), "AwardTicket(" + ticketObj.getFlightClass() + ") " + a2.getCode() + " to " + a1.getCode() + "(" + formatter.format(ticketObj.getMiles(a1, a2)) + " miles)", "$", 30.00, " $", tax, " $", total));
				sb.append(String.format("%-8s%-71s\n", "", "(" + invoice.getTicketHolder().get(j).getPerson().size() + " units @ " + milesLeft + " reward miles/unit with $30.0 ReedemptionFee)"));
			}
		
		
		
		}
		for(int k = 0; k < invoice.getServices().size(); k++){
			NumberFormat formatter = new DecimalFormat("#0.00");
			Product serviceObject = (Product) FindObject.find(invoice.getServices().get(k).getServiceCode(), productArray);
			Service serviceObj = (Service) serviceObject;
			

			
			if(serviceObject.getProductType().equals("SC")){
				double price = serviceObj.getCostPerMile();
				price = serviceObj.getFees(invoice.getServices().get(k).getUnits());
				double tax = price * .04;
				double total = price + tax;
				totalTax = totalTax + tax;
				totalSub = totalSub + price;
				totalTotal = totalTotal + total;
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n", serviceObj.getCode(), "Baggage (" + invoice.getServices().get(k).getUnits() + " @ $25.00 for 1st and $35.00 onwards)", "$", price, " $", tax, " $", total));
			}else if(serviceObject.getProductType().equals("SI")){  
				Ticket ticketObj = (Ticket) FindObject.find(invoice.getServices().get(k).getTicketCode(), productArray);
				
				Airport a1 = ticketObj.getArrAirportCode();
				Airport a2 = ticketObj.getDepAirportCode();
				
				serviceObj = (Insurance) serviceObj;
				double price = serviceObj.getCostPerMile();
				price = (price * ticketObj.getMiles(a1, a2)) * invoice.getServices().get(k).getUnits();
				double tax = price * .04;
				double total = price + tax;
				totalTax = totalTax + tax;
				totalSub = totalSub + price;
				totalTotal = totalTotal + total;
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",serviceObj.getCode(), "Insurance" + " " + serviceObj.getName() + " (" + serviceObj.getAgeClass() + ")", "$", price, " $", tax, " $", total));
				sb.append(String.format("%-8s%-71s\n", "", "(" + invoice.getServices().get(k).getUnits() + " units @ " + serviceObj.getCostPerMile() + " perMile x " + formatter.format(ticketObj.getMiles(a1, a2)) + " miles)"));
			}else if(serviceObject.getProductType().equals("SS")){
				Person personObj = (Person) FindObject.find(invoice.getServices().get(k).getPersonCode(), personArray);
				String name = personObj.getlastName() + "," + personObj.getfirstName();
 				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n",serviceObj.getCode(), "Special Assistance for" + "[" + name  + "]"  + " (" +serviceObj.getName() +")", "$", 0.00, " $", 0.00, " $", 0.00));
			}else{
				double price = serviceObj.getFees();
				price = (invoice.getServices().get(k).getUnits() * price);
				price = price - (price * .05);
				double tax = serviceObj.getTax(invoice.getServices().get(k).getUnits());
				double total = price + tax;
				totalTax = totalTax + tax;
				totalSub = totalSub + price;
				totalTotal = totalTotal + total;
				sb.append(String.format("%-8s%-71s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n", serviceObj.getCode(), serviceObj.getName() + "(" + invoice.getServices().size() + " @ $" + formatter.format(serviceObj.getFees()) + "/unit with 5% off)","$", price, " $", tax, " $", total ));
			}
			
		}
		sb.append(String.format("%110s", "===============================\n"));
		sb.append(String.format("%-20s%-59s%-2s%8.2f%-2s%8.2f%-2s%8.2f\n", "SUB-TOTALS", "", "$", totalSub, " $", totalTax, " $", totalTotal));
		if(invoice.getCustomer().getCustomerType().equals("C")){
			double discount = totalSub * .12;
			double addionFee = 40;
			totalTotal = totalTotal - discount + addionFee;
			NumberFormat formatter = new DecimalFormat("#0.00");
			String discountNeg = "-" + String.valueOf(formatter.format(discount));

			sb.append(String.format("%-40s%-39s%-2s%8.2s%-2s%8.2s%-2s%8s\n", "DISCOUNT ( 12.00% of SUBTOTAL)", "", "", "", "", "", " $", discountNeg));
			sb.append(String.format("%-20s%-59s%-2s%8.2s%-2s%8.2s%-2s%8.2f\n", "ADDITIONAL FEE", "", "", "", "", "", " $", addionFee));
			sb.append(String.format("%-20s%-59s%-2s%8.2s%-2s%8.2s%-2s%8.2f\n", "TOTAL", "", "", "", "", "", " $", totalTotal));
			subTotalEx[i] = totalSub;
			feesEx[i] = addionFee;
			taxesEx[i] = totalTax;
			discountEx[i] = discount;
			totalEx[i] = totalTotal;
			
		}else if (invoice.getCustomer().getCustomerType().equals("V")){
			double discount = totalTax;
			double addionFee = 0;
			totalTotal = totalTotal - discount;
			NumberFormat formatter = new DecimalFormat("#0.00");
			String discountNeg = "-" + String.valueOf(formatter.format(discount));
			sb.append(String.format("%-40s%-39s%-2s%8.2s%-2s%8.2s%-2s%8s\n", "DISCOUNT ( NO TAX )", "", "", "", "", "", " $", discountNeg));
			sb.append(String.format("%-20s%-59s%-2s%8.2s%-2s%8.2s%-2s%8.2f\n", "ADDITIONAL FEE", "", "", "", "", "", " $", addionFee));
			sb.append(String.format("%-20s%-59s%-2s%8.2s%-2s%8.2s%-2s%8.2f\n", "TOTAL", "", "", "", "", "", " $", totalTotal));
			subTotalEx[i] = totalSub;
			feesEx[i] = addionFee;
			taxesEx[i] = totalTax;
			discountEx[i] = discount;
			totalEx[i] = totalTotal;
		}else{
			double discount = 0;
			double addionFee = 0;
			totalTotal = totalTotal - discount;
			NumberFormat formatter = new DecimalFormat("#0.00");
			String discountNeg = "-" + String.valueOf(formatter.format(discount));
			sb.append(String.format("%-40s%-39s%-2s%8.2s%-2s%8.2s%-2s%8s\n", "DISCOUNT", "", "", "", "", "", " $", discountNeg));
			sb.append(String.format("%-20s%-59s%-2s%8.2s%-2s%8.2s%-2s%8.2f\n", "ADDITIONAL FEE", "", "", "", "", "", " $", addionFee));
			sb.append(String.format("%-20s%-59s%-2s%8.2s%-2s%8.2s%-2s%8.2f\n", "TOTAL", "", "", "", "", "", " $", totalTotal));
			subTotalEx[i] = totalSub;
			feesEx[i] = addionFee;
			taxesEx[i] = totalTax;
			discountEx[i] = discount;
			totalEx[i] = totalTotal;
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
	
	public String generateDetailReport(ArrayList<Invoice> invoiceArray, ArrayList<Person> personArray, ArrayList<Product> productArray, ArrayList<Customer> customerArray, ArrayList<String> invoiceNum, ArrayList<String> customerName, ArrayList<String> salesPerson, double[] subTotalEx, double[] feesEx, double[] taxesEx,double[] discountEx,double[] totalEx) {
	StringBuilder sb = new StringBuilder();		
	sb.append("Individual Invoice Detail Reports\n");
	sb.append("==================================================");
	
	/* TODO: Loop through all invoices and call the getTravelSummary() and 
	getCostSummary() for each invoice*/
	for(int i = 0; i < invoiceArray.size(); i++){
		
		String invoiceHeader = getInvoiceHeader(invoiceArray, i);
		String flightSummary = getTravelSummary(invoiceArray, i, productArray, personArray);
		String costSummary = getCostSummary(invoiceArray, i, personArray, productArray, customerArray, invoiceNum, customerName,  salesPerson, subTotalEx, feesEx, taxesEx, discountEx, totalEx);
		
		
		
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
		
		
		//XMLOut.toXML(personArray);
		//XMLOut.toXML(customerArray);
		//XMLOut.toXML(airportArray);
		//XMLOut.toXML(productArray);	
		//XMLOut.toXML(invoiceArray);
		
		  ArrayList<String> invoiceNum = new ArrayList();
	      ArrayList<String> customerName = new ArrayList();
	      ArrayList<String> salesPerson = new ArrayList();
	      double[] subTotalEx = new double[invoiceArray.size()];
	      double[] feesEx = new double[invoiceArray.size()];
	      double[] taxesEx = new double[invoiceArray.size()];
	      double[] discountEx = new double[invoiceArray.size()];
	      double[] totalEx = new double[invoiceArray.size()];
		
		//DataConverter.main(); //calls our DataConverter so that it runs and parses all the files.
		InvoiceReport ir = new InvoiceReport();
		String details = ir.generateDetailReport(invoiceArray, personArray, productArray, customerArray, invoiceNum, customerName, salesPerson, subTotalEx, feesEx, taxesEx, discountEx, totalEx);
		String summary = ir.generateSummaryReport(invoiceNum, customerName, salesPerson, subTotalEx, feesEx, taxesEx, discountEx, totalEx );
		
		System.out.println(summary);
		System.out.println("\n\n");
		System.out.println(details);
		
		System.out.println("======================================================================================================================");
		System.out.println("\n\n");
		
	}
}
