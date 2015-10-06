package com.airamerica.dataConversion;

import java.util.ArrayList;

import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.products.Product;
import com.airamerica.products.Ticket;

public class FindObject {
	
	public static <T> T find(String code, ArrayList<T> list){
		T found = null;


		
		
		int i = 0;
		
		if (list.getClass().isInstance(Customer.class)){	
			for(T j: list) {
							if(code.equals((list.get(i)))) {
			found = list.get(i);
				break;	
			}
				if(code.equals(((Customer) list.get(i)).getCode())) {
				found = list.get(i);
					break;	
				}
				i++;
			}
		} else if (list.getClass().isInstance(Person.class)){	
			for(T j: list) {
					
				if(code.equals(( (Person) list.get(i)).getCode())) {
				found = list.get(i);
					break;	
				}
				i++;
			}
		} else if (list.getClass().isInstance(Airport.class)){	
			for(T j: list) {
					
				if(code.equals(( (Airport) list.get(i)).getCode())) {
				found = list.get(i);
					break;	
				}
				i++;
			}
		} else if (list.getClass().isInstance(Product.class)){	
			for(T j: list) {
					
				if(code.equals(( (Product) list.get(i)).getCode())) {
				found = list.get(i);
					break;	
				}
				i++;
			}
		}
		
		return found;
		
	}
}
