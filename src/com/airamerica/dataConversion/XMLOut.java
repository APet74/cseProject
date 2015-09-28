package com.airamerica.dataConversion;

import java.io.File;
import java.io.PrintWriter;

import com.airamerica.Airports;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.Products.Products;
import com.thoughtworks.xstream.XStream;

public class XMLOut {
	
	/*
	 * XML outputs
	 */
	
	public static void personToXML(Person personArray[]){
		XStream xstream = new XStream();
		xstream.alias("person", Person.class);
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/Persons.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<persons>\n");
		
		for(int i = 0; i < personArray.length; i++){
			pw.print(xstream.toXML(personArray[i]) + "\n");
		}
		
		pw.print("</persons>" + "\n");
		pw.close();
	}
	
	public static void airportToXML(Airports airportArray[]){
		XStream xstream = new XStream();
		xstream.alias("airport", Airports.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Airports.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pw.print("<airports>\n");
		for(int i = 0; i < airportArray.length; i++){
			pw.print(xstream.toXML(airportArray[i]) + "\n");
		}
		pw.print("</airports>" + "\n");
		pw.close();
	}
	
	public static void customerToXML(Customer[] customerArray) {
		// TODO Auto-generated method stub
				XStream xstream = new XStream();
		xstream.alias("customer", Customer.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Customers.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pw.print("<customers>\n");
		for(int i = 0; i < customerArray.length; i++){
			pw.print(xstream.toXML(customerArray[i]) + "\n");
		}
		pw.print("</customers>" + "\n");
		pw.close();
	}

	
	public static void productToXML(Products [] productArray){
		XStream xstream = new XStream();
		xstream.alias("product", Products.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Products.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pw.print("<products>\n");
		for(int i = 0; i < productArray.length; i++){
			pw.print(xstream.toXML(productArray[i]) + "\n");
		}
		pw.print("</products>" + "\n");
		pw.close();
	}




}
