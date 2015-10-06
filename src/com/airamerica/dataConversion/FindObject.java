package com.airamerica.dataConversion;

import java.util.ArrayList;

import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.products.Product;
import com.airamerica.products.Ticket;

public class FindObject {
	
	@SuppressWarnings("unchecked")
	public static <T> Object find(String code, ArrayList<T> list){
		Object found = null;


		
		
		int i = 0;
		
		if (list.get(0) instanceof Customer){	
			for(T j: list) {
			
				if(code.equals(((Customer) list.get(i)).getCode())) {
				found = (Customer) list.get(i);
					break;	
				}
				i++;
			}
		} else if (list.get(0) instanceof Person){	
			for(T j: list) {
					
				if(code.equals(( (Person) list.get(i)).getCode())) {
				found = (Person) list.get(i);
					break;	
				}
				i++;
			}
		} else if (list.get(0) instanceof Airport){	
			for(T j: list) {
					
				if(code.equals(( (Airport) list.get(i)).getCode())) {
				found = (Airport) list.get(i);
					break;	
				}
				i++;
			}
		} else if (list.get(0) instanceof Product){	
			for(T j: list) {
					
				if(code.equals(( (Product) list.get(i)).getCode())) {
				found = (Product) list.get(i);
					break;	
				}
				i++;
			}
		}
		
		return found;
		
	}
}
