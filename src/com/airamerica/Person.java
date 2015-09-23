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
	private String personName;
	private Address address; //optional
	private String phoneNumber; //optional
	
	/* Note how email is used (a collection of variable size) */ 
	private List<String> emails;
	

	/*constructor(s)
	 * 
	 */
	public Person(String personCode, Address address) {
		this.personCode = personCode;
		this.address = address;
		this.emails = new ArrayList<String>();
	}
	
	// TODO: Add Getters and setters as appropriate
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getpersonName() {
		return personName;
	}

	public void setpersonName(String personName) {
		this.personName = personName;
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
