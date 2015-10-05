package com.airamerica.invoices;

import java.util.ArrayList;
import java.util.Date;

import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.products.Service;
import com.airamerica.products.Ticket;

public class Invoice {

	private String invoiceCode;
	private Customer customerCode;
	private Person personCode;
	private Date saleDate;
	private ArrayList <Ticket> tickets;
	private ArrayList <Date> flightDates;
	private ArrayList <Service> services;
	
	public Invoice(String invoiceCode, Customer customerCode, Person personCode, Date saleDate,
			ArrayList<Ticket> tickets, ArrayList<Service> services) {
		super();
		this.invoiceCode = invoiceCode;
		this.customerCode = customerCode;
		this.personCode = personCode;
		this.saleDate = saleDate;
		this.tickets = tickets;
		this.services = services;
	}
	
	public String getInvoiceCode() {
		return invoiceCode;
	}
	
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public Customer getCustomerCode() {
		return customerCode;
	}
	
	public void setCustomerCode(Customer customerCode) {
		this.customerCode = customerCode;
	}
	
	public Person getPersonCode() {
		return personCode;
	}
	
	public void setPersonCode(Person personCode) {
		this.personCode = personCode;
	}
	
	public Date getSaleDate() {
		return saleDate;
	}
	
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	
	public ArrayList<Object> getTickets() {
		return tickets;
	}
	
	public void setTickets(ArrayList<Object> tickets) {
		this.tickets = tickets;
	}
	
	public ArrayList<Service> getServices() {
		return services;
	}
	
	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}

}
