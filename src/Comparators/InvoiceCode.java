package Comparators;

import java.util.Comparator;

import com.airamerica.invoices.Invoice;

public class InvoiceCode implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		return inv2.getInvoiceCode().compareTo(inv1.getInvoiceCode());
		
	}

}
