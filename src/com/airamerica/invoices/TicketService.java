package com.airamerica.invoices;

import java.util.ArrayList;

import com.airamerica.products.Service;

public class TicketService {
	private ArrayList<Service> serviceCode;
	private ArrayList<Integer> units = null;
	private ArrayList<String> personCode = null;
	private ArrayList<String> flightNum = null;
	
	public TicketService(ArrayList<Service> serviceCode) {
		super();
		this.serviceCode = serviceCode;
	}

	public ArrayList<Service> getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(ArrayList<Service> serviceCode) {
		this.serviceCode = serviceCode;
	}

	public ArrayList<Integer> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Integer> units) {
		this.units = units;
	}

	public ArrayList<String> getPersonCode() {
		return personCode;
	}

	public void setPersonCode(ArrayList<String> personCode) {
		this.personCode = personCode;
	}

	public ArrayList<String> getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(ArrayList<String> flightNum) {
		this.flightNum = flightNum;
	}
	
	
	
}
