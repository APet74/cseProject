package com.airamerica;

public class Customer {
	
	
	private String customerCode;
	private String customerType;
	private Person primaryContact;
	private String customerName;
	private int airlineMiles;
	
	
	/* Note how Person has been used as 
	 * primary contact(Composition Relationship) */ 

	/*TODO: Add other fields as necessary (eg. firstName, lastName,
	phoneNo etc) */
	
	// TODO: Add constructor(s)
	
	/*TODO: Add Getters and setters */
	public Person getPrimaryContact() {
		return primaryContact;
	}

	//TODO: Add additional methods if needed */
}
