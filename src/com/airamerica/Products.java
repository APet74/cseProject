package com.airamerica;
	

import java.util.Date;

public class Products {

	
	/*
	 * General fields for all Products
	 */
	protected String code;
	private String productType;
	private String productName;
	
	/*
	 * SuperClass Constructor
	 */
	public Products(String code, String productType) {
		this.code = code;
		this.productType = productType;
	}
	/*
	 * Getters+Setters for Products class arguments
	 */
	
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


public class Ticket extends Products {
	
	
	/*
	 * General fields for all Tickets
	 */
	private Airports depAirportCode;
	private Airports arrAirportCode;
	
	//these might need to be in a time class 
	//rather than string:
	private Date depTime, arrTime;
	private String flightNo;
	private String flightClass;
	private String aircraftType;
	
	/*
	 * Constructor: Standard Ticket
	 */

	public Ticket(String code, String productClass, Airports depAirportCode, 
			Airports arrAirportCode, Date depTime, 
			Date arrTime, String flightNo, String flightClass,
			String aircraftType) {
		super(code,productClass);
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
		this.setProductType("TS");
		this.setProductName("Standard Ticket");
	}
 

	/*
	 * Constructor: Off Season Ticket
	 * with unique fields
	 */
	
	//will have to change this to a date format:
	private Date seasonStartDate;
	private Date seasonEndDate;
	private float rebate;

	public Ticket(String code, String productType, 
			 Airports depAirportCode, 
			Airports arrAirportCode, Date depTime, 
			Date arrTime, String flightNo, String flightClass, 
			String aircraftType, Date seasonStartDate,
			Date seasonEndDate, float rebate) {
		super(code, productType);
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
		this.rebate = rebate;
		this.setProductType("TA");
		this.setProductName("Award Ticket");
	}

	/*
	 * Constructor: Award Tickets
	 * with unique fields
	 */
	private int pointsPerMile;

	public Ticket(String code, String productType,  
			Airports depAirportCode, Airports arrAirportCode,
			Date depTime, Date arrTime, String flightNo, String flightClass, 
			String aircraftType, int pointsPerMile) {
		super(code, productType);
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
		this.pointsPerMile = pointsPerMile;
		this.setProductType("TO");
		this.setProductName("Award Ticket");
	}

	/*
	 * Getters+Setters for Tickets
	 */
	
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
	

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
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

	public float getRebate() {
		return rebate;
	}

	public void setRebate(float rebate) {
		this.rebate = rebate;
	}

	public int getPointsPerMile() {
		return pointsPerMile;
	}

	public void setPointsPerMile(int pointsPerMile) {
		this.pointsPerMile = pointsPerMile;
	}	
}
}


	