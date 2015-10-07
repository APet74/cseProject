package com.airamerica.products;

import java.util.Date;

import com.airamerica.Airport;
import com.airamerica.utils.Haversine;

public abstract class Ticket extends Product {
	
	private Airport depAirportCode;
	private Airport arrAirportCode;
	private Date depTime, arrTime;
	private String flightNo;
	private String flightClass;
	private String aircraftType;



	public Ticket(String code, String productType, Airport depAirportCode, Airport arrAirportCode,
			Date depTime, Date arrTime, String flightNo, String flightClass, String aircraftType) {
		super(code, productType);
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
	}

	public Airport getDepAirportCode() {
		return depAirportCode;
	}

	public void setDepAirportCode(Airport depAirportCode) {
		this.depAirportCode = depAirportCode;
	}

	public Airport getArrAirportCode() {
		return arrAirportCode;
	}

	public void setArrAirportCode(Airport arrAirportCode) {
		this.arrAirportCode = arrAirportCode;
	}

	public Date getDepTime() {
		return depTime;
	}

	public void setDepTime(Date depTime) {
		this.depTime = depTime;
	}

	public Date getArrTime() {
		return arrTime;
	}

	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}
	


	public double getTax(double fee) {
		return 0;
	}

	public double getFees(Airport[] airportArray) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
