package com.airamerica.dataConversion;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.invoices.Invoice;
import com.airamerica.products.Product;
import com.thoughtworks.xstream.XStream;

public class XMLOut {
	
	/*
	 * XML outputs
	 */
	

	public static <T> void toXML(ArrayList<T> list) {

		XStream xstream = new XStream();
		String header = null;
		PrintWriter pw = null;
		

		//If statement to handle generics
		if (list.get(0) instanceof Customer){

			xstream.alias("customer", Customer.class);
			
			try {
				pw = new PrintWriter(new File("data/Customers.xml"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			header = "customers";
		
		} else if (list.get(0) instanceof Product){
			
			xstream.alias("product", Product.class);
			
			try {
				pw = new PrintWriter(new File("data/Products.xml"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			header = "products";
			
		} else if (list.get(0) instanceof Airport){
			
			xstream.alias("airport", Airport.class);
			
			try {
				pw = new PrintWriter(new File("data/Airports.xml"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			header = "airports";
			
		} else if (list.get(0) instanceof Person) {
			
			xstream.alias("person", Person.class);
			
			try {
				pw = new PrintWriter(new File("data/Persons.xml"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			header = "persons";
			
		} else if (list.get(0) instanceof Invoice) {
			
			xstream.alias("invoice", Invoice.class);
			
			try {
				pw = new PrintWriter(new File("data/Invoice.xml"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			header = "invoices";
			
		}
		
		
		

		//Begin Printing
		
		pw.print("<" + header + ">\n");
		
			pw.print(xstream.toXML(list));
		
		pw.print("</" + header + ">\n");
		pw.close();
	}

}
