package com.airamerica.invoices;

import java.util.ArrayList;
import java.util.Date;

import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.dataConversion.FindObject;
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
	
	public void AddServices(ArrayList<TicketService> services) {
		this.services = services;
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

	public ArrayList <TicketHolder> getTicketHolder() {
		return ticketHolder;
	}


	public void newTicketHolder(){
		this.ticketHolder.add(new TicketHolder());
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
	
}
