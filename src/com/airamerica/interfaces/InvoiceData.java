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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.airamerica.Address;
import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.invoices.Invoice;
import com.airamerica.invoices.TicketHolder;
import com.airamerica.invoices.TicketService;
import com.airamerica.products.AwardTicket;
import com.airamerica.products.CheckedBaggage;
import com.airamerica.products.Insurance;
import com.airamerica.products.OffSeasonTicket;
import com.airamerica.products.Refreshment;
import com.airamerica.products.SpecialAssistance;
import com.airamerica.products.StandardTicket;
import com.airamerica.products.Ticket;



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
		
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		String removePersonsQuery = "DELETE FROM `Persons`";
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
		//Add static values -- online
		addStaticValues();
	}

	/**
	 * Method to add a person record to the database with the provided data. 
	 */
	public static void removeAllAddresses() {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		String removeADdressesQuery = "DELETE FROM `Addresses`";
		try{
			ps = conn.prepareStatement(removeADdressesQuery);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void addPerson(String personCode, String firstName, String lastName, 
			String phoneNo, String street, String city, String state, 
			String zip, String country) {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String addAddressQuery = "INSERT INTO `Addresses` (`street`,`city`,`state`,`zip`,`country`) VALUES (?,?,?,?,?)";
		String checkAddress = "SELECT `address_ID` FROM `Addresses` WHERE street = ? AND city = ? AND state = ? AND zip = ? AND country = ?";
		String addPersonQuery = "INSERT INTO `Persons` (`personCode`,`firstName`,`lastName`,`address_ID`,`phoneNumber`) VALUES (?,?,?,(SELECT `address_ID` FROM `Addresses` WHERE street = ? AND city = ? AND state = ? AND zip = ? AND country = ?),?)";
		try
		{
			ps = conn.prepareStatement(checkAddress);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			rs = ps.executeQuery();
			if(!(rs.next())){
				ps = conn.prepareStatement(addAddressQuery);
				ps.setString(1, street);
				ps.setString(2, city);
				ps.setString(3, state);
				ps.setString(4, zip);
				ps.setString(5, country);
				ps.executeUpdate();
				ps.close();
			}
			ps.close();
			rs.close();
			ps = conn.prepareStatement(addPersonQuery);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			firstName = firstName.replace("&apos;", "'");
			lastName = lastName.replace("&apos;", "'");
			ps.setString(4, street);
			ps.setString(5, city);
			ps.setString(6, state);
			ps.setString(7, zip);
			ps.setString(8, country);
			ps.setString(9, phoneNo);
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
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String getAddressID = "SELECT `address_ID` FROM `Airports`";
		String RemoveAddresses = "DELETE FROM `Addresses` WHERE `address_ID` = ?";
		String removeAirportsQuery = "DELETE FROM `Airports`";
		try
		{
			ps = conn.prepareStatement(getAddressID);
			rs = ps.executeQuery();
			while (rs.next()) {
				int address_ID = rs.getInt("address_ID");
				ps = conn.prepareStatement(RemoveAddresses);
				ps.setInt(1, address_ID);
				ps.executeUpdate();
			}
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
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String addAddressQuery = "INSERT INTO `Addresses` (`street`,`city`,`state`,`zip`,`country`) VALUES (?,?,?,?,?)";
		String checkAddress = "SELECT `address_ID` FROM `Addresses` WHERE street = ? AND city = ? AND state = ? AND zip = ? AND country = ?";
		String addAirportQuery = "INSERT INTO `Airports` (`airportCode`,`name`,`address_ID`,`latDegrees`,`latMinutes`,`longDegrees`,`longMinutes`,`pasFacilityFee`) VALUES (?,?,(SELECT `address_ID` FROM `Addresses` WHERE street = ? AND city = ? AND state = ? AND zip = ? AND country = ?),?,?,?,?,?)";
		try
		{
			ps = conn.prepareStatement(checkAddress);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			rs = ps.executeQuery();
			if(!(rs.next())){
				ps = conn.prepareStatement(addAddressQuery);
				ps.setString(1, street);
				ps.setString(2, city);
				ps.setString(3, state);
				ps.setString(4, zip);
				ps.setString(5, country);
				ps.executeUpdate();
				ps.close();
			}
			ps.close();
			rs.close();
			ps = conn.prepareStatement(addAirportQuery);
			ps.setString(1, airportCode);
			ps.setString(2, name);
			ps.setString(3, street);
			ps.setString(4, city);
			ps.setString(5, state);
			ps.setString(6, zip);
			ps.setString(7, country);
			ps.setInt(8, latdegs);
			ps.setInt(9, latmins);
			ps.setInt(10, londegs);
			ps.setInt(11, lonmins);
			ps.setDouble(12, passengerFacilityFee);
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
		Connection conn = DataBaseInfo.getConnection();
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
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		
		String removeCustomersQuery = "DELETE FROM `Customers`";
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
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		
		String addCustomerQuery = "INSERT INTO `Customers` (`customerCode`,`customerType`,`primaryContact_person_ID`,`customerName`,`airlineMiles`) VALUES (?,?,(SELECT `person_ID` FROM `Persons` WHERE `personCode` = ?),?,?)";
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
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;

		
		String removeTicketsQuery = "DELETE FROM `Tickets`";
		String removeServicesQuery = "DELETE FROM `Services`";
		String removeTicketTypeQuery = "DELETE FROM `ServiceTypes`";
		String removeServiceTypeQuery = "DELETE FROM `TicketTypes`";
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
			String flightNum, String flightClass, String aircraftType) { 
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketTypes WHERE `ticketType` = 'TS'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNum`,`flightClass`,`aircraftType`,`ticketType`) VALUES (?,(SELECT airport_ID FROM Airports WHERE airportCode = ?),(SELECT airport_ID FROM Airports WHERE airportCode = ?),?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TS'))";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNum);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertTicketType = "INSERT INTO `TicketTypes` (`ticketType`) VALUES ('TS')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNum`,`flightClass`,`aircraftType`,`ticketType`) VALUES (?,(SELECT airport_ID FROM Airports WHERE airportCode = ?),(SELECT airport_ID FROM Airports WHERE airportCode = ?),?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TS'))";
				ps.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNum);
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
			String depTime, String arrTime,	String flightNum, String flightClass, 
			String aircraftType, double rebate) {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketTypes WHERE `ticketType` = 'TO'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNum`,`flightClass`,`aircraftType`,`ticketType`,`seasonStartDate`,`seasonEndDate`,`rebate`) VALUES (?,(SELECT airport_ID FROM Airports WHERE airportCode = ?),(SELECT airport_ID FROM Airports WHERE airportCode = ?),?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TO'),?,?,?)";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNum);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.setString(9, seasonStartDate);
				ps.setString(10, seasonEndDate);
				ps.setDouble(11, rebate);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertTicketType = "INSERT INTO `TicketTypes` (`ticketType`) VALUES ('TO')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNum`,`flightClass`,`aircraftType`,`ticketType`,`seasonStartDate`,`seasonEndDate`,`rebate`) VALUES (?,(SELECT airport_ID FROM Airports WHERE airportCode = ?),(SELECT airport_ID FROM Airports WHERE airportCode = ?),?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TO'),?,?,?)";
				ps.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNum);
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
			String flightNum, String flightClass, 
			String aircraftType, double pointsPerMile) {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketTypes WHERE `ticketType` = 'TA'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNum`,`flightClass`,`aircraftType`,`ticketType`,`pointsPerMile`) VALUES (?,(SELECT airport_ID FROM Airports WHERE airportCode = ?),(SELECT airport_ID FROM Airports WHERE airportCode = ?),?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TA'),?)";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNum);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.setDouble(9, pointsPerMile);
				ps.executeUpdate();
				ps.close();
			}else{
				String insertTicketType = "INSERT INTO `TicketTypes` (`ticketType`) VALUES ('TA')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNum`,`flightClass`,`aircraftType`,`ticketType`,`pointsPerMile`) VALUES (?,(SELECT airport_ID FROM Airports WHERE airportCode = ?),(SELECT airport_ID FROM Airports WHERE airportCode = ?),?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TA'),?)";
				ps.close(); 
				
				ps = conn.prepareStatement(addTicketQuery);
				ps.setString(1, productCode);
				ps.setString(2, depAirportCode);
				ps.setString(3, arrAirportCode);
				ps.setString(4, depTime);
				ps.setString(5, arrTime);
				ps.setString(6, flightNum);
				ps.setString(7, flightClass);
				ps.setString(8, aircraftType);
				ps.setDouble(9, pointsPerMile);
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
		Connection conn = DataBaseInfo.getConnection();
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
				String insertServiceType = "INSERT INTO `ServiceTypes` (`serviceType`) VALUES ('Checked Baggage')";
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
		Connection conn = DataBaseInfo.getConnection();
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
				String insertServiceType = "INSERT INTO `ServiceTypes` (`serviceType`) VALUES ('Insurance')";
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
		Connection conn = DataBaseInfo.getConnection();
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
				String insertServiceType = "INSERT INTO `ServiceTypes` (`serviceType`) VALUES ('Special Assistance')";
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
		Connection conn = DataBaseInfo.getConnection();
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
				String insertServiceType = "INSERT INTO `ServiceTypes` (`serviceType`) VALUES ('Refreshment')";
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
	public static void removeAllInvoices() {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;

		
		String removeInvoiceQuery = "DELETE FROM `Invoices`";
		String removeInvoiceMapQuery = "DELETE FROM `Invoices_Tickets_map`";
		String removeTicketHolderQuery = "DELETE FROM `TicketHolders`";
		String removeTicketServiceQuery = "DELETE FROM `TicketServices`";
		try
		{
			ps = conn.prepareStatement(removeInvoiceQuery);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement(removeInvoiceMapQuery);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement(removeTicketHolderQuery);
			ps.executeUpdate();
			ps.close();
			ps = conn.prepareStatement(removeTicketServiceQuery);
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
	 * Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, 
			String salesPersonCode, String invoiceDate) {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		//if salesperson code passed is null, give it the salesperson 'online'
		System.out.println(salesPersonCode);
		
		try
		{
			String checkSalesPerson= "SELECT `Person_ID` FROM Persons WHERE `personCode` = ?";		
			ps = conn.prepareStatement(checkSalesPerson);
			ps.setString(1, salesPersonCode);
			rs = ps.executeQuery();
			
			if(!rs.next()){
				salesPersonCode = "online";
			}
			ps.close();
			rs.close();
			
			String addInvoiceQuery = "INSERT INTO `Invoices` (`invoiceCode`,`customer_ID`,`salesperson`,`saleDate`) VALUES (?,(SELECT `customer_ID` FROM `Customers` WHERE `customerCode` = ?),(SELECT `Person_ID` FROM `Persons` WHERE `personCode` = ?),?)";
			ps = conn.prepareStatement(addInvoiceQuery);
			ps.setString(1, invoiceCode);
			ps.setString(2, customerCode);
			ps.setString(3, salesPersonCode);		
			ps.setString(4, invoiceDate);
			ps.executeUpdate();
			ps.close();
				conn.close();
		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
			
	}
	
	/**
	 * Adds a particular Ticket (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * additional details
	 */
	public static void addTicketToInvoice(String invoiceCode, String productCode, 
			String travelDate, String comment) {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `Invoices_Tickets_map` (`invoice_ID`,`ticket_ID`,`comment`,`flightDate`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `ticket_ID` FROM `Tickets` WHERE `ticketCode` = ?),?,?)";
				ps = conn.prepareStatement(addTicketToInvoiceQuery);
				ps.setString(1, invoiceCode);
				ps.setString(2, productCode);
				ps.setString(3, comment);
				ps.setString(4, travelDate);
				ps.executeUpdate();
				ps.close();
				conn.close();
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Adds a Passenger information to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> 
	 */
	public static void addPassengerInformation(String invoiceCode, String productCode, 
			String personCode, 
			String identity, int age, String nationality, String seat){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketHolders` (`invoice_ID`,`ticket_ID`,`person_ID`,`identification`,`age`,`nationality`,`seatNumber`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `ticket_ID` FROM `Tickets` WHERE `ticketCode` = ?),(SELECT `person_ID` FROM `Persons` WHERE `personCode` = ?),?,?,?,?)";
				ps = conn.prepareStatement(addTicketToInvoiceQuery);
				ps.setString(1, invoiceCode);
				ps.setString(2, productCode);
				ps.setString(3, personCode);
				ps.setString(4, identity);
				ps.setInt(5, age);
				ps.setString(6, nationality);
				ps.setString(7, seat);
				ps.executeUpdate();
				ps.close();
				conn.close();
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Adds an Insurance Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity and associated ticket information
	 */
	public static void addInsuranceToInvoice(String invoiceCode, String productCode, 
			int quantity, String ticketCode) {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketServices` (`invoice_ID`,`service_ID`,`units`,`ticket_ID`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `service_ID` FROM `Services` WHERE `serviceCode` = ?),?,(SELECT ticket_ID FROM Tickets WHERE ticketCode = ?))";
				ps = conn.prepareStatement(addTicketToInvoiceQuery);
				ps.setString(1, invoiceCode);
				ps.setString(2, productCode);
				ps.setInt(3, quantity);
				ps.setString(4, ticketCode);
				ps.executeUpdate();
				ps.close();
				conn.close();
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * Adds a CheckedBaggage Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addCheckedBaggageToInvoice(String invoiceCode, String productCode, 
			int quantity) {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketServices` (`invoice_ID`,`service_ID`,`units`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `service_ID` FROM `Services` WHERE `serviceCode` = ?),?)";
				ps = conn.prepareStatement(addTicketToInvoiceQuery);
				ps.setString(1, invoiceCode);
				ps.setString(2, productCode);
				ps.setInt(3, quantity);
				ps.executeUpdate();
				ps.close();
				conn.close();
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
	/**
	 * Adds a SpecialAssistance Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addSpecialAssistanceToInvoice(String invoiceCode, String productCode, 
			String personCode) {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketServices` (`invoice_ID`,`service_ID`,`person_ID`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `service_ID` FROM `Services` WHERE `serviceCode` = ?),(SELECT `person_ID` FROM `Persons` WHERE `PersonCode` = ?))";
				ps = conn.prepareStatement(addTicketToInvoiceQuery);
				ps.setString(1, invoiceCode);
				ps.setString(2, productCode);
				ps.setString(3, personCode);
				ps.executeUpdate();
				ps.close();
				conn.close();
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Adds a Refreshment service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addRefreshmentToInvoice(String invoiceCode, 
			String productCode, int quantity) { 
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketServices` (`invoice_ID`,`service_ID`,`units`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `service_ID` FROM `Services` WHERE `serviceCode` = ?),?)";
				ps = conn.prepareStatement(addTicketToInvoiceQuery);
				ps.setString(1, invoiceCode);
				ps.setString(2, productCode);
				ps.setInt(3, quantity);
				ps.executeUpdate();
				ps.close();
				conn.close();
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static Person GetPersonObject(String personCode){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getPersonInfo = "SELECT * FROM Persons WHERE personCode = ?";
				String getAddress = "SELECT * FROM Addresses WHERE address_ID = ?";
				String getEmails = "SELECT `emailAddress` FROM EmailAddresses WHERE person_ID = ?";
				ps = conn.prepareStatement(getPersonInfo);
				ps.setString(1, personCode);
				rs = ps.executeQuery();
				rs.next();
				int personID = rs.getInt("person_ID");
				int addressID = rs.getInt("address_ID");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				firstName = firstName.replace("&apos;", "'");
				lastName = lastName.replace("&apos;", "'");
				String phoneNumber = rs.getString("phoneNumber");
				rs.close();
				ps.close();
				Person person;
				
				if (!lastName.equals("online")){
					ps = conn.prepareStatement(getAddress);
					ps.setInt(1, addressID);
					rs = ps.executeQuery();
					rs.next();
					String street = rs.getString("street");
					String city = rs.getString("city");
					String state = rs.getString("state");
					String zip = rs.getString("zip");
					String country = rs.getString("country");
					rs.close();
					ps.close();
					Address address = new Address(street, city, state, zip, country);
					person = new Person(personCode, firstName, lastName);
					person.setAddress(address);
					person.setPhoneNumber(phoneNumber);
				} else {
					person = new Person(personCode, firstName, lastName);
				}
				ps = conn.prepareStatement(getEmails);
				ps.setInt(1, personID);
				rs = ps.executeQuery();
				List<String> emails = new ArrayList<String>();
				while(rs.next()){
					String emailAddress = rs.getString("emailAddress");
					person.addEmail(emailAddress);
				}
				rs.close();
				ps.close();
				conn.close();
				return person;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Airport GetAirportObject(String airportCode){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getAirportInfo = "SELECT * FROM Airports WHERE airportCode = ?";
				String getAddress = "SELECT * FROM Addresses WHERE address_ID = ?";
				ps = conn.prepareStatement(getAirportInfo);
				ps.setString(1, airportCode);
				rs = ps.executeQuery();
				rs.next();
				int airportID = rs.getInt("airport_ID");
				int addressID = rs.getInt("address_ID");
				String name = rs.getString("name");
				int latDeg = rs.getInt("latDegrees");
				int latMin = rs.getInt("latMinutes");
				int longDegrees = rs.getInt("longDegrees");
				int longMinutes = rs.getInt("longMinutes");
				float pasFacilityFee = rs.getFloat("pasFacilityFee");
				rs.close();
				ps.close();
				ps = conn.prepareStatement(getAddress);
				ps.setInt(1, addressID);
				rs = ps.executeQuery();
				rs.next();
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String country = rs.getString("country");
				rs.close();
				ps.close();
				conn.close();
				Address address = new Address(street, city, state, zip, country);
				Airport airport = new Airport(airportCode, name, latDeg, latMin, longDegrees, longMinutes, pasFacilityFee);
				airport.setAddress(address);
				conn.close();
				return airport;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
	/*gets
	 * 
	 */
	public static List<String> getAirports(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> airports = new ArrayList<String>();
		
		try
		{
			String getAirports = "SELECT `airportCode` FROM Airports";
			ps = conn.prepareStatement(getAirports);
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				airports.add(rs.getString("airportCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return airports;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static List<String> getPersons(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> persons = new ArrayList<String>();
		
		try
		{
			String getPersons = "SELECT `personCode` FROM Persons";
			ps = conn.prepareStatement(getPersons);
			rs = ps.executeQuery();
			
			while(rs.next()){
				persons.add(rs.getString("personCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return persons;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static List<String> getCustomers(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> customers = new ArrayList<String>();
		
		try
		{
			String getCustomers = "SELECT `customerCode` FROM Customers";
			ps = conn.prepareStatement(getCustomers);
			rs = ps.executeQuery();
			
			while(rs.next()){
				customers.add(rs.getString("customerCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return customers;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static List<String> getAwardTickets(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> awardTickets = new ArrayList<String>();
		
		try
		{
			String getAwardTickets = "SELECT `ticketCode` FROM Tickets WHERE ticketType = (SELECT ticketType_ID FROM TicketTypes WHERE ticketType = 'TA')";
			ps = conn.prepareStatement(getAwardTickets);
			rs = ps.executeQuery();
			
			while(rs.next()){
				awardTickets.add(rs.getString("ticketCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return awardTickets;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static List<String> getOffSeasonTickets(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> offSeasonTickets = new ArrayList<String>();
		
		try
		{
			String getOffSeasonTickets = "SELECT `ticketCode` FROM Tickets WHERE ticketType = (SELECT ticketType_ID FROM TicketTypes WHERE ticketType = 'TO')";
			ps = conn.prepareStatement(getOffSeasonTickets);
			rs = ps.executeQuery();
			
			while(rs.next()){
				offSeasonTickets.add(rs.getString("ticketCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return offSeasonTickets;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static int getInvoiceID(String invoiceCode){
	 	Connection conn = DataBaseInfo.getConnection();
	 	PreparedStatement ps;
	 	ResultSet rs;
		int id = 0;
	 	try
	 	{
	 	String getService = "SELECT `invoice_ID` FROM Invoices WHERE `invoiceCode` = ?";
	 	ps = conn.prepareStatement(getService);
		ps.setString(1, invoiceCode);
	 	rs = ps.executeQuery();
	 	rs.next();
	 	id = rs.getInt("invoice_ID");
	 	rs.close();
	 	ps.close();
	 	conn.close();
	 	return id;
	 	}catch (SQLException e)
	 	{
	 	System.out.println("SQLException: ");
	 	e.printStackTrace();
	 	throw new RuntimeException(e);
	 	}
	 }

	public static List<String> getStandardTickets(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> standardTickets = new ArrayList<String>();
		
		try
		{
			String getStandardTickets = "SELECT `ticketCode` FROM Tickets WHERE ticketType = (SELECT ticketType_ID FROM TicketTypes WHERE ticketType = 'TS')";
			ps = conn.prepareStatement(getStandardTickets);
			rs = ps.executeQuery();
			
			while(rs.next()){
				standardTickets.add(rs.getString("ticketCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return standardTickets;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static List<String> getCheckedBaggage(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> service = new ArrayList<String>();
		
		try
		{
			String getServices = "SELECT `serviceCode` FROM Services WHERE serviceType_ID = (SELECT serviceType_ID FROM ServiceTypes WHERE serviceType = 'Checked Baggage')";
			ps = conn.prepareStatement(getServices);
			rs = ps.executeQuery();
			
			while(rs.next()){
				service.add(rs.getString("serviceCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return service;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static List<String> getInsurance(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> service = new ArrayList<String>();
		
		try
		{
			String getServices = "SELECT `serviceCode` FROM Services WHERE serviceType_ID = (SELECT serviceType_ID FROM ServiceTypes WHERE serviceType = 'Insurance')";
			ps = conn.prepareStatement(getServices);
			rs = ps.executeQuery();
			
			while(rs.next()){
				service.add(rs.getString("serviceCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return service;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static List<String> getRefreshments(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> service = new ArrayList<String>();
		
		try
		{
			String getServices = "SELECT `serviceCode` FROM Services WHERE serviceType_ID = (SELECT serviceType_ID FROM ServiceTypes WHERE serviceType = 'Refreshment')";
			ps = conn.prepareStatement(getServices);
			rs = ps.executeQuery();
			
			while(rs.next()){
				service.add(rs.getString("serviceCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return service;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static List<String> getSpecialAssistance(){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		List<String> service = new ArrayList<String>();
		
		try
		{
			String getServices = "SELECT `serviceCode` FROM Services WHERE serviceType_ID = (SELECT serviceType_ID FROM ServiceTypes WHERE serviceType = 'Special Assistance')";
			ps = conn.prepareStatement(getServices);
			rs = ps.executeQuery();
			
			while(rs.next()){
				service.add(rs.getString("serviceCode"));
			}

			rs.close();
			ps.close();
			conn.close();
			return service;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static List<String> getInvoices(){
	 	Connection conn = DataBaseInfo.getConnection();
	 	PreparedStatement ps;
	 	ResultSet rs;
	 	 
	 	List<String> invoices = new ArrayList<String>();
	 	 
	 	try
	 	{
	 	String getInvoices = "SELECT `invoiceCode` FROM Invoices";
	 	ps = conn.prepareStatement(getInvoices);
	 	rs = ps.executeQuery();
	 	 
	 	while(rs.next()){
	 	invoices.add(rs.getString("invoiceCode"));
	 	}

	 	rs.close();
	 	ps.close();
	 	conn.close();
	 	return invoices;
	 	}catch (SQLException e)
	 	{
	 	System.out.println("SQLException: ");
	 	e.printStackTrace();
	 	throw new RuntimeException(e);
	 	}
	 }
	public static List<Integer> getTicketsIDs(String invoiceCode){
	 	Connection conn = DataBaseInfo.getConnection();
	 	PreparedStatement ps;
	 	ResultSet rs;
	 	 
	 	List<Integer> tickets = new ArrayList<Integer>();
	 	 
	 	try
	 	{
	 	String getTickets = "SELECT `ticket_ID` FROM Invoices_Tickets_map WHERE `invoice_ID` = (SELECT `invoice_ID` FROM Invoices WHERE `invoiceCode` = ?)";
	 	ps = conn.prepareStatement(getTickets);
		ps.setString(1, invoiceCode);
	 	rs = ps.executeQuery();
	 	 
	 	while(rs.next()){
	 	tickets.add(rs.getInt("ticket_ID"));
	 	}

	 	
	 	rs.close();
	 	ps.close();
	 	conn.close();
	 	return tickets;
	 	}catch (SQLException e)
	 	{
	 	System.out.println("SQLException: ");
	 	e.printStackTrace();
	 	throw new RuntimeException(e);
	 	}
	 }
	public static Customer getCustomerObject(String customerCode){

		
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getCustomerInfo = "SELECT * FROM Customers WHERE customerCode = ?";
				String getPerson = "SELECT personCode FROM Persons WHERE person_ID = ?";
				ps = conn.prepareStatement(getCustomerInfo);
				ps.setString(1, customerCode);
				rs = ps.executeQuery();
				rs.next();
				int customerID = rs.getInt("customer_ID");
				int personID = rs.getInt("primaryContact_person_ID");
				String name = rs.getString("customerName");
				String custType = rs.getString("customerType");
				int airlineMiles = rs.getInt("airlineMiles");
				rs.close();
				ps.close();
				ps = conn.prepareStatement(getPerson);
				ps.setInt(1, personID);
				rs = ps.executeQuery();
				rs.next();
				String personCode = rs.getString("personCode");
				rs.close();
				ps.close();
				conn.close();
				Person person = GetPersonObject(personCode);
				Customer customer = new Customer(customerCode, custType, person, name);
				if (airlineMiles != 0) {
					customer.setAirlineMiles(airlineMiles);					
				}
				conn.close();
				return customer;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}	

	public static Ticket getTicket(String ticketCode){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		
		
		try
		{
			String getCustomers = "SELECT `ticketType` FROM TicketTypes WHERE ticketType_ID = (SELECT ticketType_ID FROM Tickets WHERE ticketCode = ?)";
			ps = conn.prepareStatement(getCustomers);
			ps.setString(1, ticketCode);
			rs = ps.executeQuery();
			Ticket t1 = null;
			rs.next();
			String ticketType = rs.getString("ticketType");

			rs.close();
			ps.close();
			conn.close();
			if(ticketType.equals("TO")){
				t1 = getOffseasonTicketObject(ticketCode);
			}else if(ticketType.equals("TA")){
				t1 = getAwardTicketObject(ticketCode);
			}else{
				t1 = getStandardTicketObject(ticketCode);
			}
			return t1;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static AwardTicket getAwardTicketObject(String ticketCode){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getTicketInfo = "SELECT * FROM Tickets WHERE ticketCode = ?";
				
				ps = conn.prepareStatement(getTicketInfo);
				ps.setString(1, ticketCode);
				rs = ps.executeQuery();
				rs.next();
				int ticketID = rs.getInt("ticket_ID");
				int depAirCode = rs.getInt("depAirportCode");
				int arrAirCode = rs.getInt("arrAirportCode");
				Date depTime = rs.getTime("depTime");
				Date arrTime = rs.getTime("arrTime");
				String flightNum = rs.getString("flightNum");
				String flightClass = rs.getString("flightClass");
				String aircraftType = rs.getString("aircraftType");
				int pointsPerMile = rs.getInt("pointsPerMile");
				rs.close();
				ps.close();
				
				String getAirportCode = "SELECT airportCode FROM Airports WHERE airport_ID = ?";
				ps = conn.prepareStatement(getAirportCode);
				ps.setInt(1, depAirCode);
				rs = ps.executeQuery();
				
				rs.next();
				String depAirName = rs.getString("airportCode");
				Airport a1 = GetAirportObject(depAirName);
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(getAirportCode);
				ps.setInt(1, arrAirCode);
				rs = ps.executeQuery();
				
				rs.next();
				String arrAirName = rs.getString("airportCode");
				Airport a2 = GetAirportObject(arrAirName);
				rs.close();
				ps.close();
				conn.close();
				AwardTicket aTicket = new AwardTicket(ticketCode, "TA", a1, a2, depTime, arrTime, flightNum, flightClass, aircraftType, pointsPerMile);
				conn.close();
				return aTicket;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static StandardTicket getStandardTicketObject(String ticketCode){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getTicketInfo = "SELECT * FROM Tickets WHERE ticketCode = ?";
				
				ps = conn.prepareStatement(getTicketInfo);
				ps.setString(1, ticketCode);
				rs = ps.executeQuery();
				rs.next();
				int ticketID = rs.getInt("ticket_ID");
				int depAirCode = rs.getInt("depAirportCode");
				int arrAirCode = rs.getInt("arrAirportCode");
				Date depTime = rs.getTime("depTime");
				Date arrTime = rs.getTime("arrTime");
				String flightNum = rs.getString("flightNum");
				String flightClass = rs.getString("flightClass");
				String aircraftType = rs.getString("aircraftType");
				rs.close();
				ps.close();
				
				String getAirportCode = "SELECT airportCode FROM Airports WHERE airport_ID = ?";
				ps = conn.prepareStatement(getAirportCode);
				ps.setInt(1, depAirCode);
				rs = ps.executeQuery();
				
				rs.next();
				String depAirName = rs.getString("airportCode");
				Airport a1 = GetAirportObject(depAirName);
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(getAirportCode);
				ps.setInt(1, arrAirCode);
				rs = ps.executeQuery();
				
				rs.next();
				String arrAirName = rs.getString("airportCode");
				Airport a2 = GetAirportObject(arrAirName);
				rs.close();
				ps.close();

				StandardTicket sTicket = new StandardTicket(ticketCode, "TS", a1, a2, depTime, arrTime, flightNum, flightClass, aircraftType);
				conn.close();
				return sTicket;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static OffSeasonTicket getOffseasonTicketObject(String ticketCode){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getTicketInfo = "SELECT * FROM Tickets WHERE ticketCode = ?";
				
				ps = conn.prepareStatement(getTicketInfo);
				ps.setString(1, ticketCode);
				rs = ps.executeQuery();
				rs.next();
				int ticketID = rs.getInt("ticket_ID");
				int depAirCode = rs.getInt("depAirportCode");
				int  arrAirCode = rs.getInt("arrAirportCode");
				Date depTime = rs.getTime("depTime");
				Date arrTime = rs.getTime("arrTime");
				String flightNum = rs.getString("flightNum");
				String flightClass = rs.getString("flightClass");
				String aircraftType = rs.getString("aircraftType");
				Date seasonStartDate = rs.getDate("seasonSTartDate");
				Date seasonEndDate = rs.getDate("seasonEndDate");
				float rebate = rs.getFloat("rebate");
				rs.close();
				ps.close();
				
				String getAirportCode = "SELECT airportCode FROM Airports WHERE airport_ID = ?";
				ps = conn.prepareStatement(getAirportCode);
				ps.setInt(1, depAirCode);
				rs = ps.executeQuery();
				
				rs.next();
				String depAirName = rs.getString("airportCode");
				Airport a1 = GetAirportObject(depAirName);
				rs.close();
				ps.close();
				
				ps = conn.prepareStatement(getAirportCode);
				ps.setInt(1, arrAirCode);
				rs = ps.executeQuery();
				
				rs.next();
				String arrAirName = rs.getString("airportCode");
				Airport a2 = GetAirportObject(arrAirName);
				rs.close();
				ps.close();
				
				OffSeasonTicket oTicket = new OffSeasonTicket(ticketCode, "TO", a1, a2, depTime, arrTime, flightNum, flightClass, aircraftType, seasonStartDate, seasonEndDate, rebate);
				conn.close();
				return oTicket;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Insurance getInsuranceObject(String code){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getServiceInfo = "SELECT * FROM Services WHERE serviceCode = ?";
				
				ps = conn.prepareStatement(getServiceInfo);
				ps.setString(1, code);
				rs = ps.executeQuery();
				rs.next();
				int serviceID = rs.getInt("service_ID");
				String name = rs.getString("serviceName");
				String ageClass = rs.getString("age");
				float costPerMile = rs.getFloat("costPerUnit");
				rs.close();
				ps.close();
				conn.close();
				Insurance insurance = new Insurance(code, "SI", name, ageClass, costPerMile);
				conn.close();
				return insurance;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static CheckedBaggage getCheckedBaggageObject(String code){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getServiceInfo = "SELECT * FROM Services WHERE serviceCode = ?";
				String getTicketCode = "SELECT ticketCode FROM Tickets WHERE ticket_ID = ?";
				ps = conn.prepareStatement(getServiceInfo);
				ps.setString(1, code);
				rs = ps.executeQuery();
				rs.next();
				int ticketID = rs.getInt("ticket_ID");
				rs.close();
				ps.close();
				ps = conn.prepareStatement(getTicketCode);
				ps.setInt(1, ticketID);
				rs = ps.executeQuery();
				rs.next();
				String ticketCode = rs.getString("ticketCode");
				rs.close();
				ps.close();
				conn.close();
				Ticket t1 = getTicket(ticketCode);
				CheckedBaggage checked = new CheckedBaggage(code, "SC", t1);
				conn.close();
				return checked;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static SpecialAssistance getSpecialAssistanceObject(String code){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getServiceInfo = "SELECT * FROM Services WHERE serviceCode = ?";
				ps = conn.prepareStatement(getServiceInfo);
				ps.setString(1, code);
				rs = ps.executeQuery();
				rs.next();
				String serviceName = rs.getString("serviceName");
				rs.close();
				ps.close();
				conn.close();
				SpecialAssistance specialA = new SpecialAssistance(code, "SS", serviceName);
				conn.close();
				return specialA;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Refreshment getRefreshmentObject(String code){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getServiceInfo = "SELECT * FROM Services WHERE serviceCode = ?";
				ps = conn.prepareStatement(getServiceInfo);
				ps.setString(1, code);
				rs = ps.executeQuery();
				rs.next();
				String serviceName = rs.getString("serviceName");
				float cost = rs.getFloat("costPerUnit");
				rs.close();
				ps.close();
				conn.close();
				Refreshment refreshment = new Refreshment(code, "SR", serviceName, cost);
				conn.close();
				return refreshment;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static List<TicketHolder> getTicketHolderObject(int ticket_ID, String invoiceCode){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps, ps1;
		ResultSet rs, rs1;
		
		List<TicketHolder> ticketHolder = new ArrayList<TicketHolder>();
		
		try
		{
			String getComment = "SELECT `comment` FROM Invoices_Tickets_map WHERE ticket_ID = ?";
			String getTicketHolder = "SELECT * FROM TicketHolders WHERE ticket_ID = ? AND invoice_ID = (SELECT invoice_ID FROM Invoices WHERE invoiceCode = ?)";
			String getPersonCode = "SELECT personCode FROM Persons WHERE person_ID = ?";
			ps = conn.prepareStatement(getComment);
			ps.setInt(1, ticket_ID);
			rs = ps.executeQuery();
			rs.next();
			String comment = rs.getString("comment");
			rs.close();
			ps.close();
			ps = conn.prepareStatement(getTicketHolder);
			ps.setInt(1, ticket_ID);
			ps.setString(2, invoiceCode);
			rs = ps.executeQuery();
			TicketHolder t1 = new TicketHolder();
			while(rs.next()){
				int age = rs.getInt("age");
				String nationality = rs.getString("nationality");
				String identification = rs.getString("identification");
				String seatNum = rs.getString("seatNumber");
				int personID = rs.getInt("person_ID");
				
				t1.addAge(age);
				t1.addId(identification);
				t1.addNationality(nationality);
				t1.addSeatNum(seatNum);
				ps1 = conn.prepareStatement(getPersonCode);
				ps1.setInt(1, personID);
				rs1 = ps1.executeQuery();
				rs1.next();
				String personCode = rs1.getString("personCode");
				t1.addPerson(personCode);
				rs1.close();
				ps1.close();
			}
			ticketHolder.add(t1);
			
			rs.close();
			ps.close();
			conn.close();
			return ticketHolder;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}	
	
	public static List<TicketService> getTicketServiceObject(int invoice_ID){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs, rs1;
		
		List<TicketService> ticketHolder = new ArrayList<TicketService>();
		
		try
		{
			String getTicketService = "SELECT * FROM TicketServices WHERE invoice_ID = ?";
			String getPersoncode = "SELECT personCode FROM Persons WHERE person_ID = ?";
			String getTicketCode = "SELECT ticketcode FROM Tickets WHERE ticket_ID = ?";
			String getServiceCode = "SELECT serviceCode FROM Services WHERE service_ID = ?";
			ps = conn.prepareStatement(getTicketService);
			ps.setInt(1, invoice_ID);
			rs = ps.executeQuery();

			while(rs.next()){
				TicketService t1 = new TicketService();
				int person_ID = rs.getInt("person_ID");
				int serviceID = rs.getInt("service_ID");
				int ticketID = rs.getInt("ticket_ID");
				int units = rs.getInt("units");
				
				t1.setUnits(units);
				
				if(!(person_ID == 0)){
					ps = conn.prepareStatement(getPersoncode);
					ps.setInt(1, person_ID);
					rs1 = ps.executeQuery();
					rs1.next();
					String PersonCode = rs1.getString("personCode");
					t1.setPersonCode(PersonCode);
					rs1.close();
					ps.close();
				}

				if(!(ticketID == 0)){
					ps = conn.prepareStatement(getTicketCode);
					ps.setInt(1, ticketID);
					rs1 = ps.executeQuery();
					rs1.next();
					String ticketCode = rs1.getString("ticketCode");
					t1.setTicketCode(ticketCode);
					rs1.close();
					ps.close();
				}
				
				ps = conn.prepareStatement(getServiceCode);
				ps.setInt(1, serviceID);
				rs1 = ps.executeQuery();
				rs1.next();
				String serviceCode = rs1.getString("serviceCode");
				t1.setServiceCode(serviceCode);
				rs1.close();
				ticketHolder.add(t1);
			}
			
			rs.close();
			ps.close();
			conn.close();
			return ticketHolder;
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}	
	
	public static Invoice getInvoiceObject(String invoiceCode){
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		ResultSet rs, rs1;
		try
		{
				String getInvoice = "SELECT * FROM Invoices WHERE invoiceCode = ?";
				String getCustomer = "SELECT customerCode FROM Customers WHERE customer_ID = ?";
				String getPerson = "SELECT personCode FROM Persons WHERE person_ID = ?";
				String getAddionInfo = "SELECT * FROM Invoices_Tickets_map WHERE invoice_ID = (SELECT invoice_ID FROM Invoices WHERE invoiceCode = ?)";
				String getTicketCode = "SELECT ticketCode FROM Tickets WHERE ticket_ID = ?";
				ps = conn.prepareStatement(getInvoice);
				ps.setString(1, invoiceCode);
				rs = ps.executeQuery();
				rs.next();
				int customerID = rs.getInt("customer_ID");
				int salesPerson = rs.getInt("salesperson");
				Date saleDate = rs.getDate("saleDate");
				rs.close();
				ps.close();
				ps = conn.prepareStatement(getCustomer);
				ps.setInt(1, customerID);
				rs = ps.executeQuery();
				rs.next();
				String customerCode = rs.getString("customerCode");
				rs.close();
				ps.close();
				ps = conn.prepareStatement(getPerson);
				ps.setInt(1, salesPerson);
				rs = ps.executeQuery();
				rs.next();
				String personCode = rs.getString("personCode");
				rs.close();
				ps.close();
				ps = conn.prepareStatement(getAddionInfo);
				ps.setString(1, invoiceCode);
				rs = ps.executeQuery();
				Invoice invoice = new Invoice();
				while(rs.next()){
					Date flightDate = rs.getDate("flightDate");
					int ticketID = rs.getInt("ticket_ID");
					String comment = rs.getString("comment");
					ps = conn.prepareStatement(getTicketCode);
					ps.setInt(1, ticketID);
					rs1 = ps.executeQuery();
					rs1.next();
					String ticketCode = rs1.getString("ticketCode");
					invoice.addComment(comment);
					invoice.addFlightDates(flightDate);
					invoice.addTicket(ticketCode);
					rs1.close();
					
				}
				rs.close();
				ps.close();
				
				conn.close();
				Customer customer = getCustomerObject(customerCode);
				
				invoice.setCustomer(customer);
				invoice.setSaleDate(saleDate);
				invoice.setSalesperson(personCode);
				invoice.setInvoiceCode(invoiceCode);
				
				conn.close();
				return invoice;
				
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}	
	
	/**
	 * Adds static values to database
	 * 
	 */
	public static void addStaticValues() {
		Connection conn = DataBaseInfo.getConnection();
		PreparedStatement ps;
		try {	
			
			
			
			
			String addStaticValuesToDatabase = "INSERT INTO `Persons` (personCode, firstName, lastName) VALUES(?,?,?)";
			ps = conn.prepareStatement(addStaticValuesToDatabase);
			ps.setString(1, "online");
			ps.setString(2, "online");
			ps.setString(3, "online");
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}catch (SQLException e)
		{
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}