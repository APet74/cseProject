package com.airamerica;
//Sync change
/* A partial implementation of address of a particular
 * Location */
public class Address {
	
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;


	/* Constructor - Generated using Eclipse Menu 
	 * (Source-> Generate Constructor using fields) */

	public Address(String street, String city, String state, 
			String zip, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		}

	public String getAddress(){
		String address = street + ", " + city + ", " + state + ", " + zip + ", " + country;
		return address;
	}
	
	public String getCityState(){
		String cityState = this.city + ", " + this.state;
		return cityState;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/* Getters and Setters - Generated using Eclipse 
	 * Menu (Source-> Generate Getters and Setters) */	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	/* Additional methods as required */
}