package com.airamerica;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Ticket extends Products {

	private Airports depAirportCode;
	private Airports arrAirportCode;
	private Date depTime;
	private Date arrTime;
	private String flightNum;
	private String flightClass;
	private String aircraftType;
	private String ticketType;
	private Date seasonStartDate;
	private Date seasonEndDate;
	private int pointsPerMile;
	
	public Airports getDepAirportCode() {
		return depAirportCode;
	}
	public void setDepAirportCode(Airports depAirportCode) {
		this.depAirportCode = depAirportCode;
	}
	public Airports getArrAirportCode() {
		return arrAirportCode;
	}
	public void setArrAirportCode(Airports arrAirportCode) {
		this.arrAirportCode = arrAirportCode;
	}
	public Date getDepTime() {
		return depTime;
	}
	public void setDepTime(Date depTime) {
		this.depTime = depTime;
	}
	public Date getArrTime() {
		return arrTime;
	}
	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}
	public String getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	public String getAircraftType() {
		return aircraftType;
	}
	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public Date getSeasonStartDate() {
		return seasonStartDate;
	}
	public void setSeasonStartDate(Date seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}
	public Date getSeasonEndDate() {
		return seasonEndDate;
	}
	public void setSeasonEndDate(Date seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}
	public int getPointsPerMile() {
		return pointsPerMile;
	}
	public void setPointsPerMile(int pointsPerMile) {
		this.pointsPerMile = pointsPerMile;
	}
	
}

