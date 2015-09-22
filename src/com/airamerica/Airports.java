package com.airamerica;

public class Airports {
	
	private String airportCode;
	private String airportName;
	private Address address;
	private int lattitudeDegrees, longitudeDegrees, longitudeMinutes, lattitudeMinutes;
	private float passengerFee;
	
	public Airports(String airportCode, String airportName, Address address, int lattitudeDegrees, int longitudeDegrees,
			int longitudeMinutes, int lattitudeMinutes, float passengerFee) {
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.address = address;
		this.lattitudeDegrees = lattitudeDegrees;
		this.longitudeDegrees = longitudeDegrees;
		this.longitudeMinutes = longitudeMinutes;
		this.lattitudeMinutes = lattitudeMinutes;
		this.passengerFee = passengerFee;
	}

	public String getAirportCode() {
		return airportCode;
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
