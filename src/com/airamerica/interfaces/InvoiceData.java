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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.airamerica.Address;
import com.airamerica.Airport;
import com.airamerica.Customer;
import com.airamerica.Person;
import com.airamerica.products.AwardTicket;
import com.airamerica.products.StandardTicket;



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
		
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
	}

	/**
	 * Method to add a person record to the database with the provided data. 
	 */
	public static void removeAllAddresses() {
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
			String flightNo, String flightClass, String aircraftType) { 
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketTypes WHERE `ticketType` = 'TS'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TS'))";
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
				String insertTicketType = "INSERT INTO `TicketTypes` (`ticketType`) VALUES ('TS')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TS'))";
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketTypes WHERE `ticketType` = 'TO'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`,`seasonStartDate`,`seasonEndDate`,`rebate`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TO'),?,?,?)";
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
				String insertTicketType = "INSERT INTO `TicketTypes` (`ticketType`) VALUES ('TO')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`,`seasonStartDate`,`seasonEndDate`,`rebate`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TO'),?,?,?)";
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		String checkTicketType = "SELECT `ticketType_ID` FROM TicketTypes WHERE `ticketType` = 'TA'";		
		try
		{
			
			ps = conn.prepareStatement(checkTicketType);
			rs = ps.executeQuery();
			if(rs.next()){
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`,`pointsPerMile`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TA'),?)";
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
				String insertTicketType = "INSERT INTO `TicketTypes` (`ticketType`) VALUES ('TA')";
				ps.close();
				rs.close();
				ps = conn.prepareStatement(insertTicketType);
				ps.executeUpdate();
				String addTicketQuery = "INSERT INTO `Tickets` (`ticketCode`,`depAirportCode`,`arrAirportCode`,`depTime`,`arrTime`,`flightNo`,`flightClass`,`aircraftType`,`ticketType`,`pointsPerMile`) VALUES (?,?,?,?,?,?,?,?,(SELECT `ticketType_ID` FROM `TicketTypes` WHERE `ticketType` = 'TA'),?)";
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		try
		{
				String addInvoiceQuery = "INSERT INTO `Invoices` (`invoiceCode`,`customer_ID`,`salesperson`,`saleDate`) VALUES (?,(SELECT `customer_ID` FROM `Customers` WHERE `customerCode` = ?),(SELECT `person_ID` FROM `Persons` WHERE `personCode` = ?),?)";
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
			String travelDate, String ticketNote) {
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `Invoices_Tickets_Map` (`invoice_ID`,`ticket_ID`,`ticketNote`,`flightDate`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `ticket_ID` FROM `Tickets` WHERE `ticketCode` = ?),?,?)";
				ps = conn.prepareStatement(addTicketToInvoiceQuery);
				ps.setString(1, invoiceCode);
				ps.setString(2, productCode);
				ps.setString(3, ticketNote);
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketHolders` (`invoice_ID`,`ticket_ID`,`person_ID`,`identification`,`age`,`nationality`,`seatNumber`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `ticket_ID` FROM `Tickets` WHERE `ticketCode` = ?),(SELECT `person_ID` FROM `Persons` WHERE `personCode` = ?),?,?,?,?)";
				ps = conn.prepareStatement(addTicketToInvoiceQuery);
				ps.setString(1, invoiceCode);
				ps.setString(2, productCode);
				ps.setString(3, personCode);
				ps.setString(4, identity);
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketServices` (`invoice_ID`,`service_ID`,`unit`,`ticket_ID`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `service_ID` FROM `Services` WHERE `serviceCode` = ?),?,(SELECT `ticket_ID` FROM `Tickets` WHERE `ticketCode` = ?))";
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketServices` (`invoice_ID`,`service_ID`,`unit`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `service_ID` FROM `Services` WHERE `serviceCode` = ?),?)";
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketServices` (`invoice_ID`,`service_ID`,`unit`,`ticket_ID`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `service_ID` FROM `Services` WHERE `serviceCode` = ?),(SELECT `person_ID` FROM `Persons` WHERE `PersonCode` = ?))";
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		try
		{
				String addTicketToInvoiceQuery = "INSERT INTO `TicketServices` (`invoice_ID`,`service_ID`,`unit`,`ticket_ID`) VALUES ((SELECT `invoice_ID` FROM `Invoices` WHERE `invoiceCode` = ?),(SELECT `service_ID` FROM `Services` WHERE `serviceCode` = ?),?)";
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
				String phoneNumber = rs.getString("phoneNumber");
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
				Person person = new Person(personCode, firstName, lastName);
				person.setAddress(address);
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
		
	public static Customer getCustomerObject(String customerCode){
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
				int personID = rs.getInt("person_ID");
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
	public static List<String> getAirports(){
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
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
	public static AwardTicket getAwardTicketObject(String ticketCode){
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getTicketInfo = "SELECT * FROM Ticket WHERE ticketType = (SELECT ticket_ID FROM TicketTypes WHERE ticketType = 'TA') AND ticketCode = ?";
				
				ps = conn.prepareStatement(getTicketInfo);
				ps.setString(1, ticketCode);
				rs = ps.executeQuery();
				rs.next();
				int ticketID = rs.getInt("ticket_ID");
				String depAirCode = rs.getString("depAirportCode");
				String arrAirCode = rs.getString("arrAirportCode");
				Date depTime = rs.getTime("depTime");
				Date arrTime = rs.getTime("arrTime");
				String flightNum = rs.getString("flightNum");
				String flightClass = rs.getString("flightClass");
				String aircraftType = rs.getString("aircraftType");
				int pointsPerMile = rs.getInt("pointsPerMile");
				rs.close();
				ps.close();
				conn.close();
				Airport a1 = GetAirportObject(depAirCode);
				Airport a2 = GetAirportObject(arrAirCode);
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
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getTicketInfo = "SELECT * FROM Ticket WHERE ticketType = (SELECT ticket_ID FROM TicketTypes WHERE ticketType = 'TS') AND ticketCode = ?";
				
				ps = conn.prepareStatement(getTicketInfo);
				ps.setString(1, ticketCode);
				rs = ps.executeQuery();
				rs.next();
				int ticketID = rs.getInt("ticket_ID");
				String depAirCode = rs.getString("depAirportCode");
				String arrAirCode = rs.getString("arrAirportCode");
				Date depTime = rs.getTime("depTime");
				Date arrTime = rs.getTime("arrTime");
				String flightNum = rs.getString("flightNum");
				String flightClass = rs.getString("flightClass");
				String aircraftType = rs.getString("aircraftType");
				rs.close();
				ps.close();
				conn.close();
				Airport a1 = GetAirportObject(depAirCode);
				Airport a2 = GetAirportObject(arrAirCode);
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
	
	/*
	public static TicketHolder getTicketHolderObject(String invoiceCode){
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getCustomerInfo = "SELECT * FROM TicketHolders WHERE customerCode = ?";
				String getPerson = "SELECT personCode FROM Persons WHERE person_ID = ?";
				ps = conn.prepareStatement(getCustomerInfo);
				ps.setString(1, customerCode);
				rs = ps.executeQuery();
				rs.next();
				int customerID = rs.getInt("customer_ID");
				int personID = rs.getInt("person_ID");
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
	*/
	
	/*public static Invoice getInvoiceObject(String invoiceCode){
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		try
		{
				String getCustomerInfo = "SELECT * FROM Customers WHERE invoiceCode = ?";
				String getPerson = "SELECT personCode FROM Persons WHERE person_ID = ?";
				ps = conn.prepareStatement(getCustomerInfo);
				ps.setString(1, customerCode);
				rs = ps.executeQuery();
				rs.next();
				int customerID = rs.getInt("customer_ID");
				int personID = rs.getInt("person_ID");
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
	*/
	/**
	 * Adds static values to database
	 * 
	 */
	public static void addStaticValues() {
		Connection conn = database.com.airamerica.interfaces.DatabaseConnect.getConnection();
		PreparedStatement ps;
		try {	
			/*
			//add static values to ServiceTypes
			//TO
			String addStaticValuesToDatabase = "INSERT INTO `TicketTypes` (`ticketType`) VALUES (?)";
			ps = conn.prepareStatement(addStaticValuesToDatabase);
			ps.setString(1, "TO");
			ps.executeUpdate();
			//TA
			addStaticValuesToDatabase = "INSERT INTO `TicketTypes` (`ticketType`) VALUES (?)";
			ps = conn.prepareStatement(addStaticValuesToDatabase);
			ps.setString(1, "TA");
			ps.executeUpdate();			
			//TS
			addStaticValuesToDatabase = "INSERT INTO `TicketTypes` (`ticketType`) VALUES (?)";
			ps = conn.prepareStatement(addStaticValuesToDatabase);
			ps.setString(1, "TS");
			ps.executeUpdate();
			*/
			//add static values to ServiceTypes
			//Refreshments
			String addStaticValuesToDatabase = "INSERT INTO `ServiceTypes` (`serviceType`) VALUES (?)";
			ps = conn.prepareStatement(addStaticValuesToDatabase);
			ps.setString(1, "Refreshments");
			ps.executeUpdate();
			//Checked Baggage
			addStaticValuesToDatabase = "INSERT INTO `ServiceTypes` (`serviceType`) VALUES (?)";
			ps = conn.prepareStatement(addStaticValuesToDatabase);
			ps.setString(1, "CheckedBaggage");
			ps.executeUpdate();
			//Insurance
			addStaticValuesToDatabase = "INSERT INTO `ServiceTypes` (`serviceType`) VALUES (?)";
			ps = conn.prepareStatement(addStaticValuesToDatabase);
			ps.setString(1, "Insurance");
			ps.executeUpdate();
			//Special Assistance
			addStaticValuesToDatabase = "INSERT INTO `ServiceTypes` (`serviceType`) VALUES (?)";
			ps = conn.prepareStatement(addStaticValuesToDatabase);
			ps.setString(1, "Special Assistance");
			ps.executeUpdate();
			
			
			
			addStaticValuesToDatabase = "INSERT INTO `Persons` (personCode, firstName, lastName) VALUES(?,?,?)";
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