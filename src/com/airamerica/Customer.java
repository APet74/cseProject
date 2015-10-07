package com.airamerica;


public class Customer {
	
	
	private String customerCode;
	private String customerType;
	private Person primaryContact;
	private String customerName;
	private int airlineMiles;
	
	
	/*Constructor
	 * 
	 */
	public Customer(String customerCode, String customerType, Person primaryContact, String customerName) {
		super();
		this.customerCode = customerCode;
		this.customerType = customerType;
		this.primaryContact = primaryContact;
		this.customerName = customerName;
	}	
	
	
	/*Getters and setters
	 * 
	 */
	public Person getPrimaryContact() {
		return primaryContact;
	}
	
	public String getCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getAirlineMiles() {
		return airlineMiles;
	}

	public void setAirlineMiles(int airlineMiles) {
		this.airlineMiles = airlineMiles;
	}

	public void setPrimaryContact(Person primaryContact) {
		this.primaryContact = primaryContact;
	}
	

	//TODO: Add additional methods if needed */
}
