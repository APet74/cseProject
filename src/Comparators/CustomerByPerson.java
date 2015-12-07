package Comparators;

import java.util.Comparator;

import com.airamerica.invoices.Invoice;

public class CustomerByPerson implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		
/*
		System.out.println(inv2.getCustomer().getPrimaryContact().getlastName().compareTo(inv1.getCustomer().getPrimaryContact().getlastName()) +
				 "\t lastName: " + inv2.getCustomer().getPrimaryContact().getlastName() + "\t :" + inv1.getCustomer().getPrimaryContact().getlastName());		
	*/
		if(inv1.getCustomer().getPrimaryContact().getlastName().compareTo(inv2.getCustomer().getPrimaryContact().getlastName()) == 0){

			return inv2.getCustomer().getPrimaryContact().getfirstName().compareTo(inv1.getCustomer().getPrimaryContact().getfirstName());
		} else {
			return inv2.getCustomer().getPrimaryContact().getlastName().compareTo(inv1.getCustomer().getPrimaryContact().getlastName());
		}

	}

	//sort by person w/in Customer w/in Invoice
}
