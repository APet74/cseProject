package Comparators;

import java.util.Comparator;

import com.airamerica.Person;
import com.airamerica.interfaces.InvoiceData;
import com.airamerica.invoices.Invoice;

public class CustomertypeBySalesperson implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		
		if(inv1.getCustomer().getCustomerType().compareTo(inv2.getCustomer().getCustomerType()) == 0){
			Person salesperson1 = null;
			Person salesperson2 = null;
			salesperson1 = InvoiceData.GetPersonObject(inv1.getSalesperson());
			salesperson2 = InvoiceData.GetPersonObject(inv2.getSalesperson());
			
			if (salesperson1.getlastName().compareTo(salesperson2.getlastName()) == 0){
				return salesperson1.getfirstName().compareTo(salesperson2.getfirstName());
			} else {
				return salesperson1.getlastName().compareTo(salesperson2.getlastName());
			}
		} else {
			return inv1.getCustomer().getCustomerType().compareTo(inv2.getCustomer().getCustomerType());
		}

	}

	//sort by customer type, then by salesperson

}
