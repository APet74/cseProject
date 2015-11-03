package com.airamerica.interfaces;
/* Assignment 5 - (Phase IV) */
/* NOTE: Donot change the package name or any of the method signatures.
 *  
 * There are 23 methods in total, all of which need to be completed as a 
 * bare minimum as part of the assignment.You can add additional methods 
 * for testing if you feel.
 * 
 * It is also recommended that you write a separate program to read
 * from the .dat files and test these methods to insert data into your 
 * database.
 * 
 * Donot forget to change your reports generation classes to read from 
 * your database instead of the .dat files.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.airamerica.database.DatabaseConnect;


/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class InvoiceData {

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		
		String removePersonsQuery = "DELETE * FROM `Persons`";
		try
		{
			ps = conn.prepareStatement(removePersonsQuery);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to add a person record to the database with the provided data. 
	 */
	public static void addPerson(String personCode, String firstName, String lastName, 
			String phoneNo, String street, String city, String state, 
			String zip, String country) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		
		String addAddressQuery = "INSERT INTO `Addresses` (`street`,`city`,`state`,`zip`,`country`) VALUES (?,?,?,?,?)";
		String addPersonQuery = "INSERT INTO `Persons` (`personCode`,`firstName`,`lastName`,`address_ID`,`phoneNo`) VALUES (?,?,?,(SELECT `address_ID` FROM `Addresses` ORDER BY `address_ID` desc LIMIT 1),?)";
		try
		{
			ps = conn.prepareStatement(addAddressQuery);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement(addPersonQuery);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, phoneNo);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method that removes every airport record from the database
	 */
	public static void removeAllAirports() {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		
		String removeAirportsQuery = "DELETE * FROM `Airports`";
		try
		{
			ps = conn.prepareStatement(removeAirportsQuery);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method to add a airport record to the database with the provided data. 
	 */
	public static void addAirport(String airportCode, String name, String street, 
			String city, String state, String zip, String country, 
			int latdegs, int latmins, int londegs, int lonmins, 
			double passengerFacilityFee) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		
		String addAddressQuery = "INSERT INTO `Addresses` (`street`,`city`,`state`,`zip`,`country`) VALUES (?,?,?,?,?)";
		String addAirportQuery = "INSERT INTO `Persons` (`airportCode`,`name`,`address_ID`,`latDegrees`,`latMinutes`,`longDegrees`,`longMinutes`,`pasFacilityFee`) VALUES (?,?,(SELECT `address_ID` FROM `Addresses` ORDER BY `address_ID` desc LIMIT 1),?,?,?,?,?)";
		try
		{
			ps = conn.prepareStatement(addAddressQuery);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement(addAirportQuery);
			ps.setString(1, airportCode);
			ps.setString(2, name);
			ps.setInt(3, latdegs);
			ps.setInt(4, latmins);
			ps.setInt(5, londegs);
			ps.setInt(6, lonmins);
			ps.setDouble(7, passengerFacilityFee);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 */
	public static void addEmail(String personCode, String email) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		
		String addEmailQuery = "INSERT INTO `EmailAddresses` (`person_ID`,`emailAddress`) VALUES ((SELECT `person_ID` FROM `Persons` WHERE `personCode` = ?),?)";
		try
		{
			ps = conn.prepareStatement(addEmailQuery);
			ps.setString(1, personCode);
			ps.setString(2, email);
			ps.executeUpdate();
			ps.close();

			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		
		String removeCustomersQuery = "DELETE * FROM `Customers`";
		try
		{
			ps = conn.prepareStatement(removeCustomersQuery);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to add a customer record to the database with the provided data. 
	 */
	public static void addCustomer(String customerCode, String customerType, 
			String primaryContactPersonCode, String name, 
			int airlineMiles) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		
		String addCustomerQuery = "INSERT INTO `Persons` (`customerCode`,`customerType`,`primaryContact_person_ID`,`customerName`,`airlineMiles`) VALUES (?,?,SELECT `person_ID` FROM `Persons` WHERE `personCode` = ?,?,?,?,?,?)";
		try
		{
			ps = conn.prepareStatement(addCustomerQuery);
			ps.setString(1, customerCode);
			ps.setString(2, customerType);
			ps.setString(3, primaryContactPersonCode);
			ps.setString(4, name);
			ps.setInt(5, airlineMiles);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;

		
		String removeTicketsQuery = "DELETE * FROM `Tickets`";
		String removeServicesQuery = "DELETE * FROM `Services`";
		String removeTicketTypeQuery = "DELETE * FROM `ServiceTypes`";
		String removeServiceTypeQuery = "DELETE * FROM `TicketTypes`";
		try
		{
			ps = conn.prepareStatement(removeTicketsQuery);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement(removeServicesQuery);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement(removeServiceTypeQuery);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement(removeTicketTypeQuery);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds an standardTicket record to the database with the
	 * provided data.  
	 */
	public static void addStandardTicket(String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType) { 
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketType WHERE `ticketType` = 'TS'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`) VALUES (?,?,?,?,?,?,?,?,SELECT `ticketType_ID` FROM `TicketType` WHERE `ticketType` = 'TS')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNo);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertTicketType = "INSTER INTO `TicketType` (`ticketType`) VALUES ('TS')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`) VALUES (?,?,?,?,?,?,?,?,SELECT `ticketType_ID` FROM `TicketType` WHERE `ticketType` = 'TS')";
				ps.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNo);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.executeUpdate();
				ps.close();
				
			}

			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
	
	 /** 
	 * Adds an offSeasonTicket record to the database with the
	 * provided data.  
	 */
	public static void addOffSeasonTicket(String productCode, String seasonStartDate, 
			String seasonEndDate, String depAirportCode, String arrAirportCode, 
			String depTime, String arrTime,	String flightNo, String flightClass, 
			String aircraftType, double rebate) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketType WHERE `ticketType` = 'TO'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`,`seasonStartDate`,`seasonEndDate`,`rebate`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketType` WHERE `ticketType` = 'TO'),?,?,?)";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNo);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.setString(9, seasonStartDate);
				ps.setString(10, seasonEndDate);
				ps.setDouble(11, rebate);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertTicketType = "INSTER INTO `TicketType` (`ticketType`) VALUES ('TO')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`,`seasonStartDate`,`seasonEndDate`,`rebate`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketType` WHERE `ticketType` = 'TO'),?,?,?)";
				ps.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNo);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.setString(9, seasonStartDate);
				ps.setString(10, seasonEndDate);
				ps.setDouble(11, rebate);
				ps.executeUpdate();
				ps.close();
				
			}

			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	 
	 /** Adds an awardsTicket record to the database with the
	 * provided data.  
	 */
	public static void addAwardsTicket(String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, 
			String aircraftType, int pointsPerMile) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketType WHERE `ticketType` = 'TA'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`,`pointsPerMile`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketType` WHERE `ticketType` = 'TA'),?)";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNo);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.setInt(9, pointsPerMile);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertTicketType = "INSTER INTO `TicketType` (`ticketType`) VALUES ('TA')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`,`pointsPerMile`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketType` WHERE `ticketType` = 'TA'),?)";
				ps.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNo);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.setInt(9, pointsPerMile);
				ps.executeUpdate();
				ps.close();
				
			}

			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	} 
	
	/**
	 * Adds a CheckedBaggage record to the database with the
	 * provided data.  
	 */
	public static void addCheckedBaggage(String productCode, String ticketCode) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkServiceType = "SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `serviceType` = 'Checked Baggage'";		
		try
		{
			
			ps = conn.prepareStatement(checkServiceType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addServiceQuery = "INSERT INTO `Services` (`serviceType_ID`,`serviceCode`,`ticket_ID`) VALUES ((SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `ServiceType` = 'Checked Baggage'),?,(SELECT `ticket_ID` From `Tickets` WHERE `ticketCode` = ?))";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addServiceQuery);
				ps.setString(1, productCode);
				ps.setString(2, ticketCode);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertServiceType = "INSTER INTO `ServiceTypes` (`serviceType`) VALUES ('Checked Baggage')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertServiceType);
				ps.executeUpdate();
				String addServiceQuery = "INSERT INTO `Services` (`serviceType_ID`,`serviceCode`,`ticket_ID`) VALUES ((SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `ServiceType` = 'Checked Baggage'),?,(SELECT `ticket_ID` From `Tickets` WHERE `ticketCode` = ?))";
				ps.close();
				ps = conn.prepareStatement(addServiceQuery);
				ps.setString(1, productCode);
				ps.setString(2, ticketCode);
				ps.executeUpdate();
				ps.close();
				
			}

			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a Insurance record to the database with the
	 * provided data.  
	 */
	public static void addInsurance(String productCode, String productName, 
			String ageClass, double costPerMile) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkServiceType = "SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `serviceType` = 'Insurance'";		
		try
		{
			
			ps = conn.prepareStatement(checkServiceType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addServiceQuery = "INSERT INTO `Services` (`serviceType_ID`,`serviceCode`,`costPerUnit`,`age`,`serviceName`) VALUES ((SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `ServiceType` = 'Insurance'),?,?,?,?)";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addServiceQuery);
				ps.setString(1, productCode);
				ps.setDouble(2, costPerMile);
				ps.setString(3, ageClass);
				ps.setString(4, productName);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertServiceType = "INSTER INTO `ServiceTypes` (`serviceType`) VALUES ('Insurance')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertServiceType);
				ps.executeUpdate();
				String addServiceQuery = "INSERT INTO `Services` (`serviceType_ID`,`serviceCode`,`costPerUnit`,`age`,`serviceName`) VALUES ((SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `ServiceType` = 'Insurance'),?,?,?,?)";
				ps.close();
				ps = conn.prepareStatement(addServiceQuery);
				ps.setString(1, productCode);
				ps.setDouble(2, costPerMile);
				ps.setString(3, ageClass);
				ps.setString(4, productName);
				ps.executeUpdate();
				ps.close();
				
			}

			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Adds a SpecialAssistance record to the database with the
	 * provided data.  
	 */
	public static void addSpecialAssistance(String productCode, String assistanceType) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkServiceType = "SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `serviceType` = 'Special Assistance'";		
		try
		{
			
			ps = conn.prepareStatement(checkServiceType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addServiceQuery = "INSERT INTO `Services` (`serviceType_ID`,`serviceCode`,`serviceName`) VALUES ((SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `ServiceType` = 'Special Assistance'),?,?)";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addServiceQuery);
				ps.setString(1, productCode);
				ps.setString(2, assistanceType);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertServiceType = "INSTER INTO `ServiceTypes` (`serviceType`) VALUES ('Special Assistance')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertServiceType);
				ps.executeUpdate();
				String addServiceQuery = "INSERT INTO `Services` (`serviceType_ID`,`serviceCode`,`serviceName`) VALUES ((SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `ServiceType` = 'Special Assistance'),?,?)";
				ps.close();
				ps = conn.prepareStatement(addServiceQuery);
				ps.setString(1, productCode);
				ps.setString(2, assistanceType);
				ps.executeUpdate();
				ps.close();
				
			}

			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * Adds a refreshment record to the database with the
	 * provided data.  
	 */
	public static void addRefreshment(String productCode, String name, double cost) {
		Connection conn = DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkServiceType = "SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `serviceType` = 'Refreshment'";		
		try
		{
			
			ps = conn.prepareStatement(checkServiceType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addServiceQuery = "INSERT INTO `Services` (`serviceType_ID`,`serviceCode`,`costPerUnit`,`serviceName`) VALUES ((SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `ServiceType` = 'Refreshment'),?,?,?)";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addServiceQuery);
				ps.setString(1, productCode);
				ps.setDouble(2, cost);
				ps.setString(3, name);

				ps.executeUpdate();
				ps.close();
			}else{
				String insertServiceType = "INSTER INTO `ServiceTypes` (`serviceType`) VALUES ('Refreshment')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertServiceType);
				ps.executeUpdate();
				String addServiceQuery = "INSERT INTO `Services` (`serviceType_ID`,`serviceCode`,`costPerUnit`,`serviceName`) VALUES ((SELECT `serviceType_ID` FROM `ServiceTypes` WHERE `ServiceType` = 'Refreshment'),?,?,?)";
				ps.close();
				ps = conn.prepareStatement(addServiceQuery);
				ps.setString(1, productCode);
				ps.setDouble(2, cost);
				ps.setString(3, name);
				ps.executeUpdate();
				ps.close();
				
			}

			conn.close();
		}
		catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() { }
	
	/**
	 * Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, 
			String salesPersonCode, String invoiceDate) { }
	
	/**
	 * Adds a particular Ticket (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * additional details
	 */
	public static void addTicketToInvoice(String invoiceCode, String productCode, 
			String travelDate, String ticketNote) { }
	
	/**
	 * Adds a Passenger information to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> 
	 */
	public static void addPassengerInformation(String invoiceCode, String productCode, 
			String personCode, 
			String identity, int age, String nationality, String seat){ }
	
	/**
	 * Adds an Insurance Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity and associated ticket information
	 */
	public static void addInsuranceToInvoice(String invoiceCode, String productCode, 
			int quantity, String ticketCode) { }

	/**
	 * Adds a CheckedBaggage Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addCheckedBaggageToInvoice(String invoiceCode, String productCode, 
			int quantity) { }
		
	/**
	 * Adds a SpecialAssistance Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addSpecialAssistanceToInvoice(String invoiceCode, String productCode, 
			String personCode) { }
	
	/**
	 * Adds a Refreshment service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addRefreshmentToInvoice(String invoiceCode, 
			String productCode, int quantity) { }
}