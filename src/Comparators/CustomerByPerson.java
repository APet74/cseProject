package Comparators;

import java.util.Comparator;

import com.airamerica.invoices.Invoice;

public class CustomerByPerson implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		
		if(inv1.getCustomer().getPrimaryContact().getlastName().compareTo(inv2.getCustomer().getPrimaryContact().getlastName()) == 0){
			return inv1.getCustomer().getPrimaryContact().getfirstName().compareTo(inv2.getCustomer().getPrimaryContact().getfirstName());
		} else {
			return inv1.getCustomer().getPrimaryContact().getlastName().compareTo(inv2.getCustomer().getPrimaryContact().getlastName());
		}

	}

	//sort by person w/in Customer w/in Invoice
}
