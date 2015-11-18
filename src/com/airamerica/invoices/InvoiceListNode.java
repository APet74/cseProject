package com.airamerica.invoices;

public class InvoiceListNode {

	private InvoiceListNode next = null;
	private Invoice invoice;
	
	public InvoiceListNode(Invoice invoice) {
		super();
		this.invoice = invoice;
	}
		
}
