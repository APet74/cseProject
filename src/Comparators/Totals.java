package Comparators;

import java.util.Comparator;

import com.airamerica.invoices.Invoice;

public class Totals implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		System.out.println("Code: " + inv1.getInvoiceCode() + "\tTotal: " + inv1.getTotal(inv1)+ "\nCode: " + inv2.getInvoiceCode() + "\tTotal: " + inv2.getTotal(inv2) );
		
		if(inv2.getTotal(inv2) > inv1.getTotal(inv1)){
			return 1;
		}else if(inv2.getTotal(inv2) < inv1.getTotal(inv1)){
			return -1;
		}
		else{
			return 0;
		}

	}

}
