package database.com.airamerica.interfaces;

import java.util.ArrayList;

import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
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

public class DatabaseImportSystem {

	public static void clearDatabase(){
		InvoiceData.removeAllAddresses();
		InvoiceData.removeAllAirports();
		InvoiceData.removeAllCustomers();
		InvoiceData.removeAllInvoices();
		InvoiceData.removeAllPersons();
		InvoiceData.removeAllProducts();
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
		}
		//TODO: add emails
		
		
	}
	

	public static void uploadCustomers(ArrayList<Customer> customerArray){
		//From Customer object to database
		for(Customer c: customerArray){
			InvoiceData.addCustomer(c.getCode(), c.getCustomerType(), c.getPrimaryContact().getCode(), c.getCustomerName(), c.getAirlineMiles());
		}
	}
	
	public static void uploadProducts(ArrayList<Product> productArray){
		//From products object to database
		
		//have to sort between product types
		  //Ticket  Award, OffSeason, Standard
		  //Service  CheckedBaggage, Insurance, Refreshment, SpecialAssistance
		for(Product p: productArray){
			if(p instanceof AwardTicket){
				AwardTicket t = (AwardTicket) p;
				InvoiceData.addAwardsTicket(t.getCode(), t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), 
						t.getDepTime().toString(), t.getArrTime().toString(), t.getFlightNo(), t.getFlightClass(), t.getAircraftType(), 
						t.getPointsPerMile());
				
			} else if (p instanceof OffSeasonTicket){
				OffSeasonTicket t = (OffSeasonTicket) p;
				InvoiceData.addOffSeasonTicket(t.getCode(), t.getSeasonStartDate().toString(), t.getSesaonEndDate().toString(), 
						t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), t.getDepTime().toString(), 
						t.getArrTime().toString(), t.getFlightNo(), t.getFlightClass(), t.getAircraftType(), t.getRebate());
				
			} else if (p instanceof StandardTicket){
				/*
				 * String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType
				 */
				StandardTicket t = (StandardTicket) p;
				InvoiceData.addStandardTicket(t.getCode(), t.getDepAirportCode().getAirportCode(), t.getArrAirportCode().getAirportCode(), 
						t.getDepTime().toString(), t.getArrTime().toString(), t.getFlightNo(), t.getFlightClass(), t.getAircraftType());
				
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
		
		//add every invoice object to database
		for (Invoice i: invoiceArray){
			InvoiceData.addInvoice(i.getInvoiceCode(), i.getCustomer().getCode(), i.getSalesperson(), i.getSaleDate().toString());
			
			//add every ticket to the invoice
			for (int k = 0; k < i.getTicketCodes().size(); k++){
				//add ticket at k
				InvoiceData.addTicketToInvoice(i.getInvoiceCode(), i.getTicketCodes(k), i.getFlightDates(k).toString(), i.getComment(k));
				
				//add ticketholder at k
				for(int l = 0; l < i.getTicketHolder(k).getSeatNum().size(); l++){
					InvoiceData.addPassengerInformation(i.getInvoiceCode(), i.getTicketCodes(k), 
							i.getTicketHolder(k).getPerson(l), i.getTicketHolder(k).getId(l),
							i.getTicketHolder(k).getAge(l), i.getTicketHolder(k).getNationality(l),
							i.getTicketHolder(k).getSeat(l));
				}
				
				//add ticketservice at k
			}
			
		}
 	}
}