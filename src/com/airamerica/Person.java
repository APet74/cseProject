package com.airamerica;
/*
/* A partial implementation representing a 
 * Person */
import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private String personCode;
	
	//Changed to personName due to confusion within 
	//Products class -- Insurance name
	private String firstName, lastName;
	private Address address; //optional
	private String phoneNumber; //optional
	
	/* Note how email is used (a collection of variable size) */ 
	private List<String> emails = new ArrayList<String>();
	

	/*constructor(s)
	 * 
	 */
	public Person(String personCode, String firstName, String lastName) {
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// TODO: Add Getters and setters as appropriate
	public String getAddress() {
		return this.address.getAddress();
	}
	
	public Address getAddressObject(){
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

	// TODO: Add additional methods here
	public void addEmail(String email) {
		this.emails.add(email);
	}

	//TODO: return a formatted list of emails, or return one
	//		email at a time
	public List<String> getEmails() {
		return emails;
	}
	
}