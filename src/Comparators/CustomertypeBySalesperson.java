package Comparators;

import java.util.Comparator;

import com.airamerica.invoices.Invoice;

public class CustomertypeBySalesperson implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		// TODO Auto-generated method stub
		if(inv1.getCustomer().getCustomerType().compareTo(inv2.getCustomer().getCustomerType()) == 0){
			
		}
		
		return 0;
	}

	//sort by customer type, then by salesperson

}
