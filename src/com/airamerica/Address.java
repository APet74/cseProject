package com.airamerica;
//Sync change
/* A partial implementation of address of a particular
 * Location */
public class Address {
	
	private String street;
	private String city;
	private String State;
	private String zip;
	private String Country;


	/* Constructor - Generated using Eclipse Menu 
	 * (Source-> Generate Constructor using fields) */

	public Address(String street, String city) {
		this.street = street;
		this.city = city;
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