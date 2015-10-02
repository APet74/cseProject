package com.airamerica.products;

public class CheckedBaggage extends Service {
	private Ticket ticketCode;

	public CheckedBaggage(String code, String productType, Ticket ticketCode) {
		super(code, productType);
		this.ticketCode = ticketCode;
	}

	public Ticket getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(Ticket ticketCode) {
		this.ticketCode = ticketCode;
	}
	
	
}
