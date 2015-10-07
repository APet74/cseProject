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
	
	
	
	public Invoice() {
		super();
	}

	public Invoice(String invoiceCode) {
		this.invoiceCode = invoiceCode;
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

	public ArrayList <Date> getFlightDates() {
		return flightDates;
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
}
