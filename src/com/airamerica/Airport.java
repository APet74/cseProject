package com.airamerica;


public class Airport {
	
	private String airportCode;
	private String airportName;
	private Address address;
	private int lattitudeDegrees, longitudeDegrees, longitudeMinutes, lattitudeMinutes;
	private float passengerFee;
	
	public Airport(String airportCode, String airportName, int lattitudeDegrees, int lattitudeMinutes,
			int longitudeDegrees,
			int longitudeMinutes,  float passengerFee) {
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.lattitudeDegrees = lattitudeDegrees;
		this.longitudeDegrees = longitudeDegrees;
		this.longitudeMinutes = longitudeMinutes;
		this.lattitudeMinutes = lattitudeMinutes;
		this.passengerFee = passengerFee;
	}

	public String getCode() {
		return airportCode;
	}
	
	public String getCityState(){
		String cityState = this.address.getCityState();
		
		return cityState;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getLattitudeDegrees() {
		return lattitudeDegrees;
	}

	public void setLattitudeDegrees(int lattitudeDegrees) {
		this.lattitudeDegrees = lattitudeDegrees;
	}

	public int getLongitudeDegrees() {
		return longitudeDegrees;
	}

	public void setLongitudeDegrees(int longitudeDegrees) {
		this.longitudeDegrees = longitudeDegrees;
	}

	public int getLongitudeMinutes() {
		return longitudeMinutes;
	}

	public void setLongitudeMinutes(int longitudeMinutes) {
		this.longitudeMinutes = longitudeMinutes;
	}

	public int getLattitudeMinutes() {
		return lattitudeMinutes;
	}

	public void setLattitudeMinutes(int lattitudeMinutes) {
		this.lattitudeMinutes = lattitudeMinutes;
	}

	public float getPassengerFee() {
		return passengerFee;
	}

	public void setPassengerFee(float passengerFee) {
		this.passengerFee = passengerFee;
	}
	
	
}
