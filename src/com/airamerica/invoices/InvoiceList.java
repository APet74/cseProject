package com.airamerica.invoices;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;


public class InvoiceList implements Iterable <Invoice>{

	private InvoiceListNode start = null;
	private InvoiceListNode end = null;
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
			end = newNode;
			size++;
		} else {
			//else compare to nodes, find place for node
			InvoiceListNode prevNode = start;
			InvoiceListNode nextNode = start.getNext();
			boolean flag = true;
			int count = 0;
			while (compareInvoiceList.compare(prevNode.getInvoice(), newNode.getInvoice()) > 0 && flag) {
				if (nextNode == null){
					this.end = newNode;
					flag = false;
					System.out.println("1");
					}else {
					prevNode = nextNode;
					nextNode = prevNode.getNext();
					
				}

			}

			prevNode.setNext(newNode);
			newNode.setNext(nextNode);
		}
		
	}
	
	//not functional to have a remove - all invoices pulled from database, there are no invoice reports that show only a segment of all invoices

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
