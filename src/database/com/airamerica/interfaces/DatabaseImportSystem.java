package database.com.airamerica.interfaces;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.dataConversion.FindObject;
import com.airamerica.interfaces.InvoiceData;
import com.airamerica.invoices.Invoice;
import com.airamerica.products.AwardTicket;
import com.airamerica.products.CheckedBaggage;
import com.airamerica.products.Insurance;
import com.airamerica.products.OffSeasonTicket;
import com.airamerica.products.Product;
import com.airamerica.products.Refreshment;
import com.airamerica.products.SpecialAssistance;
import com.airamerica.products.StandardTicket;
import com.airamerica.products.Ticket;

public class DatabaseImportSystem {

	/*
	 * import data into database
	 */
	public static void clearDatabase(){
		//TODO: remove all emails
		InvoiceData.removeAllAddresses();
		InvoiceData.removeAllAirports();
		InvoiceData.removeAllCustomers();
		InvoiceData.removeAllInvoices();
		InvoiceData.removeAllPersons();
		InvoiceData.removeAllProducts();
		
		InvoiceData.addStaticValues();
	}
	
	public static void uploadAirports(ArrayList<Airport> airportArray){
		//From airport object to database
		for(Airport a: airportArray){
			InvoiceData.addAirport(a.getAirportCode(), a.getAirportName(), a.getAddress().getStreet(), a.getAddress().getCity(),
					a.getAddress().getState(), a.getAddress().getZip(), a.getAddress().getCountry(), a.getLattitudeDegrees(), 
					a.getLattitudeMinutes(), a.getLongitudeDegrees(), a.getLongitudeMinutes(), (float)a.getPassengerFee());
		}
	}
	
	public static void uploadPersons(ArrayList<Person> personArray){
		//From person object to database
		for(Person p: personArray){
			InvoiceData.addPerson(p.getCode(), p.getfirstName(), p.getlastName(), p.getPhoneNumber(), p.getAddressObject().getStreet(),
					p.getAddressObject().getCity(), p.getAddressObject().getState(), p.getAddressObject().getZip(), p.getAddressObject().getCountry());
		
			//add every email to the person in database
			for(String e: p.getEmails()){
				InvoiceData.addEmail(p.getCode(), e);
			}
		}
	}
	
	public static void uploadCustomers(ArrayList<Customer> customerArray){
		//From Customer object to database
		for(Customer c: customerArray){
			InvoiceData.addCustomer(c.getCode(), c.getCustomerType(), c.getPrimaryContact().getCode(), c.getCustomerName(), c.getAirlineMiles());
		}
	}
	
	public static void uploadProducts(ArrayList<Product> productArray){
		//From products object to database
		
		DateFormat timeOfDay = new SimpleDateFormat("HH:mm");
		DateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
		
		//have to sort between product types
		  //Ticket  Award, OffSeason, Standard
		  //Service  CheckedBaggage, Insurance, Refreshment, SpecialAssistance
		for(Product p: productArray){
			if(p instanceof AwardTicket){
				AwardTicket t = (AwardTicket) p;
				InvoiceData.addAwardsTicket(t.getCode(), t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), 
						timeOfDay.format(t.getFlightTime("departing")), timeOfDay.format(t.getFlightTime("arriving")), t.getFlightNo(), t.getFlightClass(), t.getAircraftType(), 
						t.getPointsPerMile());
				
			} else if (p instanceof OffSeasonTicket){
				OffSeasonTicket t = (OffSeasonTicket) p;
				InvoiceData.addOffSeasonTicket(t.getCode(), yearMonthDay.format(t.getSeasonStartDate()), yearMonthDay.format(t.getSesaonEndDate()), 
						t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), timeOfDay.format(t.getFlightTime("departing")), 
						timeOfDay.format(t.getFlightTime("arriving")), t.getFlightNo(), t.getFlightClass(), t.getAircraftType(), t.getRebate());
				
			} else if (p instanceof StandardTicket){
				/*
				 * String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType
				 */
				StandardTicket t = (StandardTicket) p;
				InvoiceData.addStandardTicket(t.getCode(), t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), 
						timeOfDay.format(t.getFlightTime("departing")), timeOfDay.format(t.getFlightTime("arriving")), t.getFlightNo(), t.getFlightClass(), t.getAircraftType());
				
			} else if (p instanceof CheckedBaggage){
				/*
				 * String productCode, String ticketCode
				 */
				CheckedBaggage s = (CheckedBaggage) p;
				InvoiceData.addCheckedBaggage(s.getCode(), s.getTicketCode().getCode());
				
			} else if (p instanceof Insurance){
				/*
				 * String productCode, String productName, 
			String ageClass, double costPerMile
				 */
				Insurance s = (Insurance) p;
				InvoiceData.addInsurance(s.getCode(), s.getName(), s.getAgeClass(), (double) s.getCostPerMile());
				
			} else if (p instanceof Refreshment){
				/*
				 * String productCode, String name, double cost
				 */
				Refreshment s = (Refreshment) p;
				InvoiceData.addRefreshment(s.getCode(), s.getName(), (double) s.getCost()); 
				
			} else if (p instanceof SpecialAssistance){
				/*
				 * String productCode, String assistanceType
				 */
				SpecialAssistance s = (SpecialAssistance) p;
				InvoiceData.addSpecialAssistance(s.getCode(), s.getTypeOfService());
				
			}
		}
	}
	
 	public static void uploadInvoices(ArrayList<Invoice> invoiceArray, ArrayList<Product> productArray){
		DateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
		
		//add every invoice object to database
		for (Invoice i: invoiceArray){
			InvoiceData.addInvoice(i.getInvoiceCode(), i.getCustomer().getCode(), i.getSalesperson(), yearMonthDay.format(i.getSaleDate()));
		
			//add every ticket to the invoice
			for (int k = 0; k < i.getTicketCodes().size(); k++){
				//add ticket at k
				InvoiceData.addTicketToInvoice(i.getInvoiceCode(), i.getTicketCodes(k), yearMonthDay.format(i.getFlightDates(k)), i.getComment(k));
				
				//add ticketholder at k
				for(int l = 0; l < i.getTicketHolder(k).getSeatNum().size(); l++){
					InvoiceData.addPassengerInformation(i.getInvoiceCode(), i.getTicketCodes(k), 
							i.getTicketHolder(k).getPerson(l), i.getTicketHolder(k).getId(l),
							i.getTicketHolder(k).getAge(l), i.getTicketHolder(k).getNationality(l),
							i.getTicketHolder(k).getSeat(l));
				}
			}
			
			//add ticketservice at k
			for(int l = 0; l < i.getServices().size(); l++){
				if(FindObject.find(i.getServices().get(l).getServiceCode(), productArray) instanceof CheckedBaggage){
					InvoiceData.addCheckedBaggageToInvoice(i.getInvoiceCode(), i.getServices().get(l).getServiceCode(), i.getServices().get(l).getUnits());
				} else if (FindObject.find(i.getServices().get(l).getServiceCode(), productArray) instanceof Insurance){
					InvoiceData.addInsuranceToInvoice(i.getInvoiceCode(), i.getServices().get(l).getServiceCode(), i.getServices().get(l).getUnits());	
				} else if (FindObject.find(i.getServices().get(l).getServiceCode(), productArray) instanceof Refreshment){
					InvoiceData.addRefreshmentToInvoice(i.getInvoiceCode(), i.getServices().get(l).getServiceCode(), i.getServices().get(l).getUnits());
				} else if (FindObject.find(i.getServices().get(l).getServiceCode(), productArray) instanceof SpecialAssistance){
					InvoiceData.addSpecialAssistanceToInvoice(i.getInvoiceCode(), i.getServices().get(l).getServiceCode(), i.getServices().get(l).getPersonCode());
				}		
			}
			
		}
 	}

 	/*
 	 * download data from database
 	 */
 	public static ArrayList<Airport> downloadAirports(){
 		ArrayList<Airport> airportArray = new ArrayList<Airport>();
 		
 		List<String> airportCodes = InvoiceData.getAirports();
 		
 		for(String s: airportCodes){
 			airportArray.add(InvoiceData.GetAirportObject(s));
 		}
 		
 		return airportArray;
 	}

 	public static ArrayList<Person> downloadPersons(){
 		ArrayList<Person> personArray = new ArrayList<Person>();
 		
 		List<String> personCodes = InvoiceData.getPersons();
 		
 		for(String s: personCodes){
 			personArray.add(InvoiceData.GetPersonObject(s));
 		}
 		
 		return personArray;
 	}
 	
 	public static ArrayList<Customer> downloadCustomers(){
 		ArrayList<Customer> customerArray = new ArrayList<Customer>();
 		
 		List<String> customerCodes = InvoiceData.getCustomers();
 		
 		for(String s: customerCodes){
 			customerArray.add(InvoiceData.getCustomerObject(s));
 		}
 		
 		return customerArray;
 	}

 	public static ArrayList<Product> downloadProducts(){
 		ArrayList<Product> productArray = new ArrayList<Product>();
 		
 		List<String> products = InvoiceData.getAwardTickets();

 		for(String s: products){
 			productArray.add(InvoiceData.getAwardTicketObject(s));
 		}
 		
 		products = InvoiceData.getOffSeasonTickets();
 		
 		for(String s: products){
 			productArray.add(InvoiceData.getOffseasonTicketObject(s));
 		}
 		
 		products = InvoiceData.getStandardTickets();
 		
 		for(String s: products){
 			productArray.add(InvoiceData.getStandardTicketObject(s));
 		}
 		
 		products = InvoiceData.getCheckedBaggage();
 		
 		for(String s: products){
 			productArray.add(InvoiceData.getCheckedBaggageObject(s));
 		}
 		
 		products = InvoiceData.getInsurance();
 		
 		for(String s: products){
 			productArray.add(InvoiceData.getInsuranceObject(s));
 		}
 		
 		products = InvoiceData.getRefreshments();
 		
 		for(String s: products){
 			productArray.add(InvoiceData.getRefreshmentObject(s));
 		}
 		
 		products = InvoiceData.getSpecialAssistance();
 		
 		for(String s: products){
 			productArray.add(InvoiceData.getSpecialAssistanceObject(s));
 		}
 		
 		return productArray;
 	}
 	
 	public static ArrayList<Invoice> downloadInvoices(){
 		ArrayList<Invoice> invoiceArray = new ArrayList<Invoice>();
 		
 		List<String> invoices= InvoiceData.getInvoices();

 		for(String s: invoices){
 			invoiceArray.add(InvoiceData.getInvoiceObject(s));
 		}
 		
 		//for every invoice array 
 		
 		return invoiceArray;
 	}
 	
}