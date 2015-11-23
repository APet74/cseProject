package com.airamerica.invoices;

import java.util.Arrays;


public class InvoiceList{

	private InvoiceListNode start = null;
	private InvoiceListNode end = null;
	private int size = 0;
	
	public InvoiceList() {
		super();
	}
	
	//when pulled from database, added to list
	public void add(Invoice invoice){
		InvoiceListNode newNode = new InvoiceListNode(invoice);
		
		//if first, make into start node
		//else make into 
	}
	
	//not functional to have a remove - all invoices pulled from database, there are no invoice reports that show only a segment of all invoices

	public Invoice get(int index){
		//TODO
	}
	
	public int size(){
		return this.size;
	}

	private static void quickSortRecursive(Invoice list[], int low, int high) {
		int i = low;
		int j = high;
		int pivot = (((i - j)/2)+j);
		while(i <= j){
			while(list[i].compareTo(list[pivot]) > 0){
				i++;
			}
			while(list[j].compareTo(list[pivot]) < 0){
				j--;
			}
			if(i <= j){
				Location temp = list[i];
				list[i] = list[j];
				list[j] = temp;
				i++;
				j--;
			}
		}
		if(low < j){
			quickSortRecursive(list, low, j);
		}
		if(i < high){
			quickSortRecursive(list, i, high);
		}
	}
	
	
}
