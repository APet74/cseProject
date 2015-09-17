package com.airamerica;
/*
/* A partial implementation representing a 
 * Person */
import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private String personCode;
	private String name;
	private Address address;
	private String phoneNumber;
	
	/* Note how email is used (a collection of variable size) */ 
	private List<String> emails;
	

	// TODO: Add appropriate constructor(s)
	public Person(String personCode, Address address) {
		this.personCode = personCode;
		this.address = address;
		this.emails = new ArrayList<String>();
	}
	
	// TODO: Add Getters and setters as appropriate
	public Address getAddress() {
		return this.address;
	}
	
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
		
	// TODO: Add additional methods here
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
}
