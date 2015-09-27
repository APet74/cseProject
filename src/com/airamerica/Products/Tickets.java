package com.airamerica.Products;

import java.util.Date;

import com.airamerica.Airports;

public abstract class Tickets extends Products {
	
	private Airports depAirportCode;
	private Airports arrAirportCode;
	private Date depTime, arrTime;
	private String flightNo;
	private String flightClass;
	private String aircraftType;



	public Tickets(String code, String productType, Airports depAirportCode, Airports arrAirportCode,
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

	public Airports getDepAirportCode() {
		return depAirportCode;
	}

	public void setDepAirportCode(Airports depAirportCode) {
		this.depAirportCode = depAirportCode;
	}

	public Airports getArrAirportCode() {
		return arrAirportCode;
	}

	public void setArrAirportCode(Airports arrAirportCode) {
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
	
}
