package com.airamerica.Products;

public class CheckedBaggage extends Services {
	private Tickets ticketCode;

	public CheckedBaggage(String code, String productType, Tickets ticketCode) {
		super(code, productType);
		this.ticketCode = ticketCode;
	}

	public Tickets getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(Tickets ticketCode) {
		this.ticketCode = ticketCode;
	}
	
	
}
