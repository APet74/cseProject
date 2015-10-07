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
	
	@Override
	public double getFees(int amount){
		double fee = 0;
		for(int i = 0; i < amount; i++){
			if(i == 0){
				fee = 25;
			}else{
				fee += 25;
			}
		}
		return fee;
	}
	
}
