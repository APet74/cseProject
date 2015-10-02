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
	private ArrayList <Service> services;
}
