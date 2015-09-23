package com.airamerica;
	

import java.util.Date;

public class Products {

	
	/*
	 * General fields for all Products
	 */
	private String code;
	private String productType;
	private String productName;
	
	/*
	 * Getters+Setters for Products class arguments
	 */
	
	public String getCode() {
		return code;
	}

	public Products(String code, String productType, String productName) {
		super();
		this.code = code;
		this.productType = productType;
		this.productName = productName;
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


	/*
	 * Ticket Subclass
	 */
	public class Ticket extends Products {
		
		
		/*
		 * General fields for all Tickets
		 */
		private String depAirportCode;
		private String arrAirportCode;
		
		//these might need to be in a time class 
		//rather than string:
		private Date depTime, arrTime;
		private String flightClass;
		private String aircraftType;
		
		/*
		 * Constructor: Standard Ticket
		 */
		public Ticket(String depAirportCode, 
				String arrAirportCode, Date depTime, 
				Date arrTime, String flightClass,
				String aircraftType) {
			super(code, productType, productName);
			this.depAirportCode = depAirportCode;
			this.arrAirportCode = arrAirportCode;
			this.depTime = depTime;
			this.arrTime = arrTime;
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
				String productName, String depAirportCode, 
				String arrAirportCode, Date depTime, 
				Date arrTime, String flightClass, 
				String aircraftType, Date seasonStartDate,
				Date seasonEndDate, float rebate) {
			super(code, productType, productName);
			this.depAirportCode = depAirportCode;
			this.arrAirportCode = arrAirportCode;
			this.depTime = depTime;
			this.arrTime = arrTime;
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

		public Ticket(String code, String productType, String productName, 
				String depAirportCode, String arrAirportCode,
				Date depTime, Date arrTime, String flightClass, 
				String aircraftType, int pointsPerMile) {
			super(code, productType, productName);
			this.depAirportCode = depAirportCode;
			this.arrAirportCode = arrAirportCode;
			this.depTime = depTime;
			this.arrTime = arrTime;
			this.flightClass = flightClass;
			this.aircraftType = aircraftType;
			this.pointsPerMile = pointsPerMile;
			this.setProductType("TO");
			this.setProductName("Award Ticket");
		}

		/*
		 * Getters+Setters for Tickets
		 */
		
		public String getDepAirportCode() {
			return depAirportCode;
		}

		public void setDepAirportCode(String depAirportCode) {
			this.depAirportCode = depAirportCode;
		}

		public String getArrAirportCode() {
			return arrAirportCode;
		}

		public void setArrAirportCode(String arrAirportCode) {
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
	
	/*
	 * Services Subclass
	 */
	public class Services extends Products {
		


		/*
		 * Checked Baggage
		 */
		private int ticketCode;

		public Services(String code, String productType, String productName, int ticketCode) {
			super(code, productType, productName);
			this.ticketCode = ticketCode;
		}
		
		public int getTicketCode() {
			return ticketCode;
		}

		public void setTicketCode(int ticketCode) {
			this.ticketCode = ticketCode;
		}

		//This is weird, and I'm not sure how to solve the problem
		//checked baggage and special assistance have the same
		//number and the same kinds of fields.
		//I can't do overloaded polymorphism with this since 
		//everything is a string. I guess I'll just leave out the 
		//ticket code and type of service and use getters/setters
		//to solve the problem..
		
		
		
		/*
		 * Special Assistance
		 */
		private String typeOfService;
		
		public Services(String code, String productType, String productName, String typeOfService) {
			super(code, productType, productName);
			this.typeOfService = typeOfService;
		}
		
		public String getTypeOfService() {
			return typeOfService;
		}

		public void setTypeOfService(String typeOfService) {
			this.typeOfService = typeOfService;
		}
		
		/*
		 * Insurance
		 */
		private String insuranceName;
		private String ageClass;
		private float costPerMile;
		
		public Services(String code, String productType, String productName, String insuranceName,
				String ageClass, float costPerMile) {
			super(code, productType, productName);
			this.insuranceName = insuranceName;
			this.ageClass = ageClass;
			this.costPerMile = costPerMile;
		}

		public String getInsuranceName() {
			return insuranceName;
		}

		public void setInsuranceName(String insuranceName) {
			this.insuranceName = insuranceName;
		}

		public String getAgeClass() {
			return ageClass;
		}

		public void setAgeClass(String ageClass) {
			this.ageClass = ageClass;
		}

		public float getCostPerMile() {
			return costPerMile;
		}

		public void setCostPerMile(float costPerMile) {
			this.costPerMile = costPerMile;
		}
		
		/*
		 * Refreshments
		 */
		private String refreshmentName;
		private float cost;
		
		public Services(String code, String productType, String productName, String refreshmentName,
				float cost) {
			super(code, productType, productName);
			this.refreshmentName = refreshmentName;
			this.cost = cost;
		}

		public String getRefreshmentName() {
			return refreshmentName;
		}

		public void setRefreshmentName(String refreshmentName) {
			this.refreshmentName = refreshmentName;
		}

		public float getCost() {
			return cost;
		}

		public void setCost(float cost) {
			this.cost = cost;
		}
		
	}
}



