package com.airamerica.invoices;

public class InvoiceListNode implements Comparable{

	private InvoiceListNode next = null;
	private Invoice invoice;
	
	public InvoiceListNode(Invoice invoice) {
		super();
		this.invoice = invoice;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
		
    public Invoice getInvoice() {
        return invoice;
    }

    public InvoiceListNode getNext() {
        return next;
    }

    public void setNext(InvoiceListNode next) {
        this.next = next;
    }
}
