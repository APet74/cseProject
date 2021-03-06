package com.airamerica.invoices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.dataConversion.FindObject;
import com.airamerica.interfaces.InvoiceData;
import com.airamerica.products.Insurance;
import com.airamerica.products.Product;
import com.airamerica.products.Service;
import com.airamerica.products.Ticket;

public class Invoice {

	private String invoiceCode;
	private Customer customer;
	private String salespersonCode;
	private Date saleDate;
	private ArrayList <String> ticketCodes = new ArrayList<String>();
	private ArrayList <Date> flightDates = new ArrayList<Date>();
	private ArrayList <TicketHolder> ticketHolder = new ArrayList<TicketHolder>();
	private ArrayList <TicketService> services = new ArrayList<TicketService>();
	private ArrayList <String> comments = new ArrayList<>();
	
	
	public Invoice() {
		super();
	}

	public Invoice(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public String getTicketCodes(int index) {
		return ticketCodes.get(index);
	}
	public ArrayList<String> getTicketCodes() {
		return ticketCodes;
	}
	public void setTicketCodes(ArrayList<String> ticketCodes) {
		this.ticketCodes = ticketCodes;
	}
	
	public int getTicketCodesSize(){
		return this.ticketCodes.size();
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}
	
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	public void setCustomer(String customerCode, ArrayList<Customer> list) {
		this.customer = (Customer) FindObject.find(customerCode, list);
	}
	
	public String getSalesperson() {
		return salespersonCode;
	}
	
	public void setSalesperson(String personCode) {
			this.salespersonCode = personCode;
	}
	
	public Date getSaleDate() {
		return saleDate;
	}
	
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	
	
	public ArrayList<TicketService> getServices() {
		return services;
	}
	
	public void AddServices(List<TicketService> list) {
		this.services = (ArrayList<TicketService>) list;
	}

	public void addTicket(String code){
		this.ticketCodes.add(code);
	}

	public Date getFlightDates(int index) {
		return flightDates.get(index);
	}

	public void addFlightDates(Date flightDates) {
		this.flightDates.add(flightDates);
	}

	public ArrayList<TicketHolder> getTicketHolder(){
		return this.ticketHolder;
	}
	public TicketHolder getTicketHolder(int index){
		return this.ticketHolder.get(index);
	}	
	public String getTicketHolderName(int index, int indexB, ArrayList<Person> personArray) {
		return ticketHolder.get(index).getName(indexB, personArray);
	}


	public void newTicketHolder(){
		this.ticketHolder.add(new TicketHolder());
	}
	public void addTicketHolders(List<TicketHolder> ticketHolder){
		this.ticketHolder.addAll(ticketHolder);
	}
	
	public void addTicketHolderInformation(int index, String seatNumber, String personCode, String id, Integer age, String nationality) {
		this.ticketHolder.get(index).addSeatNum(seatNumber);
		this.ticketHolder.get(index).addPerson(personCode);
		this.ticketHolder.get(index).addId(id);
		this.ticketHolder.get(index).addAge(age);
		this.ticketHolder.get(index).addNationality(nationality);
	}
	
	public void newTicketService(){
		this.services.add(new TicketService());
	}
	
	public void addCheckedBaggage(int index, String serviceCode, int units){
		this.services.get(index).setServiceCode(serviceCode);
		this.services.get(index).setUnits(units);
	}
	
	public void addInsurance(int index, String serviceCode, int units, String ticketCode) {
		this.services.get(index).setServiceCode(serviceCode);
		this.services.get(index).setUnits(units);
		this.services.get(index).setTicketCode(ticketCode);
		
	}
	
	public void addRefreshment(int index, String serviceCode, int units){
		this.services.get(index).setServiceCode(serviceCode);
		this.services.get(index).setUnits(units);

	}
	
	public void addSpecialAssistance(int index, String serviceCode, String personCode){
		this.services.get(index).setServiceCode(serviceCode);
		this.services.get(index).setPersonCode(personCode);
	}
	
	public void addComment(String comment){
		this.comments.add(comment);
	}
	
	public String getComment(int index) {
		return this.comments.get(index);
	}
	

	
	public int getNumberOfPassengers (int index) {
		return this.ticketHolder.get(index).getNumberOfPassengers();
	}
	public double getTotal(Invoice invoice){
		double total = 0;
		for(int j = 0; j < invoice.getTicketCodesSize(); j++){	
			Ticket ticketObj = InvoiceData.getTicket(invoice.getTicketCodes(j));
			Airport a1 = ticketObj.getArrAirportCode();
			if(ticketObj.getProductType().equals("TS")){
				double fees = ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size();
				double tax = ticketObj.getTax(fees) + (4 * invoice.getTicketHolder().get(j).getPerson().size()) + (5.6 * invoice.getTicketHolder().get(j).getPerson().size() + (a1.getPassengerFee() * invoice.getTicketHolder().get(j).getPerson().size()));
				total += fees + tax;
			}else if(ticketObj.getProductType().equals("TO")){
				if((ticketObj.getSeasonStartDate().compareTo(invoice.getFlightDates(j)) * invoice.getFlightDates(j).compareTo(ticketObj.getSesaonEndDate()) > 0) || (invoice.getFlightDates(j).equals(ticketObj.getSeasonStartDate()) || (invoice.getFlightDates(j).equals(ticketObj.getSesaonEndDate())))){
					double fee = (ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size()) - ((ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size()) * ticketObj.getRebate()) + 20 ;
				    double tax = ticketObj.getTax(fee) + (4 * invoice.getTicketHolder().get(j).getPerson().size()) + (5.6 * invoice.getTicketHolder().get(j).getPerson().size() + (a1.getPassengerFee() * invoice.getTicketHolder().get(j).getPerson().size()));
						//adds totals and sub totals
						total += fee + tax;
				}else{
					double fee = (ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size()) + 20 ;
					double tax = ticketObj.getTax(fee) + (4 * invoice.getTicketHolder().get(j).getPerson().size()) + (5.6 * invoice.getTicketHolder().get(j).getPerson().size() + (a1.getPassengerFee() * invoice.getTicketHolder().get(j).getPerson().size()));
						//math for totals and sub totals
					 	total += fee + tax;
				}
			}else{
				double fee = ticketObj.getFees() * invoice.getTicketHolder().get(j).getPerson().size();
				 double tax = ticketObj.getTax(30) + (4 * invoice.getTicketHolder().get(j).getPerson().size()) + (5.6 * invoice.getTicketHolder().get(j).getPerson().size() + (a1.getPassengerFee() * invoice.getTicketHolder().get(j).getPerson().size()));
					//math for totals and sub totals
				 	total += 30 + tax;
			}
		}
		for(int k = 0; k < invoice.getServices().size(); k++){
			Service serviceObj = InvoiceData.getService(invoice.getServices().get(k).getServiceCode());
			if(serviceObj.getProductType().equals("SC")){
				//checks if baggage and if so grabs prices and calculates fees and taxes calculations from 330 to 336
				double price = serviceObj.getCostPerMile();
				price = serviceObj.getFees(invoice.getServices().get(k).getUnits());
				double tax = price * .04;
				total += price + tax;
				
			}else if(serviceObj.getProductType().equals("SI")){  
				//if it is insurance grabs a ticket to get the air miles and calculates how much the insurance costs, calculations from line 348 to 354
				Ticket ticketObj = (Ticket) InvoiceData.getTicket(invoice.getServices().get(k).getTicketCode());
				Airport a1 = ticketObj.getArrAirportCode();
				Airport a2 = ticketObj.getDepAirportCode();
				serviceObj = (Insurance) serviceObj;
				double price = serviceObj.getCostPerMile();
				price = (price * ticketObj.getMiles(a1, a2)) * invoice.getServices().get(k).getUnits();
				double tax = price * .04;
				total += price + tax;
			}else if(serviceObj.getProductType().equals("SR")){
				//if here then must be a refreshment, math is done from 368 to 375
				double price = serviceObj.getFees();
				price = (invoice.getServices().get(k).getUnits() * price);
				price = price - (price * .05);
				double tax = serviceObj.getTax(invoice.getServices().get(k).getUnits());
				total += price + tax;
			}
		}
		if(invoice.getCustomer().getCustomerType().equals("V")){
			double discount = total * .12;
			double addFee = 40;
			total = total - discount + addFee;
		}
		return total;
	}
}