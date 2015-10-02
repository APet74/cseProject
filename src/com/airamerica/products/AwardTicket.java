package com.airamerica.products;

import java.util.Date;

import com.airamerica.Airport;

public class AwardTicket extends Ticket {
	private int pointsPerMile;

	public AwardTicket(String code, String productType, Airport depAirportCode, Airport arrAirportCode, Date depTime2,
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
