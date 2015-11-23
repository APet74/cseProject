package Comparators;

import java.util.Comparator;

import com.airamerica.invoices.Invoice;

public class InvoiceCode implements Comparator<Invoice>{

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		return inv1.getInvoiceCode().compareTo(inv2.getInvoiceCode());
		
	}

}
