package com.airamerica;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Products {

	private String productCode;
	private String productType;
	
	

	public Products(String productCode, String productType) {
		super();
		this.productCode = productCode;
		this.productType = productType;
	}



	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductType(){
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
	public class Ticket extends Products {

		private Date seasonStartDate;
		private Date seasonEndDate;
		private Airports depAirportCode;
		private Airports arrAirportCode;
		private Date depTime;
		private Date arrTime;
		private String flightNum;
		private String flightClass;
		private String aircraftType;
		private int pointsPerMile;
		private float rebate;
		
		public Ticket(String productCode, String productType, Airports depAirportCode, Airports arrAirportCode,
				Date depTime, Date arrTime, String flightNum, String flightClass, String aircraftType) {
			super(productCode, productType);
			this.depAirportCode = depAirportCode;
			this.arrAirportCode = arrAirportCode;
			this.depTime = depTime;
			this.arrTime = arrTime;
			this.flightNum = flightNum;
			this.flightClass = flightClass;
			this.aircraftType = aircraftType;
		}
		
		public Ticket(String productCode, String productType, Date seasonStartDate, Date seasonEndDate,
				Airports depAirportCode, Airports arrAirportCode, Date depTime, Date arrTime, String flightNum,
				String flightClass, String aircraftType, float rebate) {
			super(productCode, productType);
			this.seasonStartDate = seasonStartDate;
			this.seasonEndDate = seasonEndDate;
			this.depAirportCode = depAirportCode;
			this.arrAirportCode = arrAirportCode;
			this.depTime = depTime;
			this.arrTime = arrTime;
			this.flightNum = flightNum;
			this.flightClass = flightClass;
			this.aircraftType = aircraftType;
			this.rebate = rebate;
		}

		public Ticket(String productCode, String productType, Airports depAirportCode, Airports arrAirportCode,
				Date depTime, Date arrTime, String flightNum, String flightClass, String aircraftType, int pointsPerMile) {
			super(productCode, productType);
			this.depAirportCode = depAirportCode;
			this.arrAirportCode = arrAirportCode;
			this.depTime = depTime;
			this.arrTime = arrTime;
			this.flightNum = flightNum;
			this.flightClass = flightClass;
			this.aircraftType = aircraftType;
			this.pointsPerMile = pointsPerMile;
		}

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
		public float getRebate() {
			return rebate;
		}
		public void setRebate(float rebate) {
			this.rebate = rebate;
		}
		
	}
	
	public class Services extends Products {
		
		private int ticketCode;
		private String name;
		private String ageClass;
		private float costPerMile;
		private String typeofService;
		private float cost;
			
		public Services(String productCode, String productType, int ticketCode) {
			super(productCode, productType);
			this.ticketCode = ticketCode;
		}
		
		public Services(String productCode, String productType, String typeofService) {
			super(productCode, productType);
			this.typeofService = typeofService;
		}

		public Services(String productCode, String productType, String name, String ageClass, float costPerMile) {
			super(productCode, productType);
			this.name = name;
			this.ageClass = ageClass;
			this.costPerMile = costPerMile;
		}


		public Services(String productCode, String productType, String name, float cost) {
			super(productCode, productType);
			this.name = name;
			this.cost = cost;
		}

		public int getTicketCode() {
			return ticketCode;
		}
		public void setTicketCode(int ticketCode) {
			this.ticketCode = ticketCode;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getTypeofService() {
			return typeofService;
		}
		public void setTypeofServer(String typeofService) {
			this.typeofService = typeofService;
		}
		public float getCost() {
			return cost;
		}
		public void setCost(float cost) {
			this.cost = cost;
		}
		
	}

	
}


