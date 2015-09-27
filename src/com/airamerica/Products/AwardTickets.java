package com.airamerica.Products;

import java.util.Date;

import com.airamerica.Airports;

public class AwardTickets extends Tickets {
	private int pointsPerMile;

	public AwardTickets(String code, String productType, Airports depAirportCode, Airports arrAirportCode, Date depTime2,
			Date arrTime2, String flightNo, String flightClass, String aircraftType, int pointsPerMile) {
		super(code, productType, depAirportCode, arrAirportCode, depTime2, arrTime2, flightNo, flightClass,
				aircraftType);
		this.pointsPerMile = pointsPerMile;
	}

	public int getPointsPerMile() {
		return pointsPerMile;
	}

	public void setPointsPerMile(int pointsPerMile) {
		this.pointsPerMile = pointsPerMile;
	}
	
	
	
}
