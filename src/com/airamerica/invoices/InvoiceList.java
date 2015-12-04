package com.airamerica.invoices;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;


public class InvoiceList implements Iterable <Invoice>{

	private InvoiceListNode start = null;
	private int size = 0;
	
	public InvoiceList() {
		super();
	}
	
	//when pulled from database, added to list
	public void add(Invoice invoice, Comparator<Invoice> compareInvoiceList){
		InvoiceListNode newNode = new InvoiceListNode(invoice);
		
		//if first, make into start node
		if (size == 0){
			start = newNode;
			size++;
		} else {
			//else compare to nodes, find place for node
			InvoiceListNode prevNode = start;
			InvoiceListNode nextNode = start;
			boolean flag = true;
			int count = 0;
			//if statement == || correct order with start
			if (compareInvoiceList.compare(nextNode.getInvoice(), newNode.getInvoice()) == 0 ||
					compareInvoiceList.compare(nextNode.getInvoice(), newNode.getInvoice()) > 0){
				start = newNode;
				start.setNext(nextNode);
			} else {
				while(!(nextNode.getNext() == null)){
					prevNode = nextNode;
					nextNode = nextNode.getNext();				
				
					if(compareInvoiceList.compare(nextNode.getInvoice(), newNode.getInvoice())  > -1) {
						break;
					}
				}
				
				if((nextNode.getNext() == null)){
					nextNode.setNext(newNode);
				} else {
					prevNode.setNext(newNode);
					newNode.setNext(nextNode);
				}
			}
			//if statement else -- loop to find correct location
			
			size++;
		}
		
	}
	

	public Invoice get(int index){
		//TODO
		int count = 0;
		InvoiceListNode node = start;
		while (count < index) {
			node = node.getNext();
			count++;
		}
		return node.getInvoice();
	}
	
	public int size(){
		return this.size;
	}

	@Override
	public Iterator<Invoice> iterator() {
		return new InvoiceListIterator();
	}

	//inner iterable class
	private class InvoiceListIterator implements Iterator<Invoice>{
		private InvoiceListNode placeholder;
		
		public InvoiceListIterator() {
			this.placeholder = InvoiceList.this.start;
		}

		@Override
		public boolean hasNext() {
			return (!(placeholder.getNext() == null));
		}

		@Override
		public Invoice next() {
			// TODO Auto-generated method stub
			if(this.hasNext()) {
				InvoiceListNode thisNode = placeholder;
				placeholder = placeholder.getNext();
				return thisNode.getInvoice();
			}
			
			return null;
		}
	}

	
	
}
