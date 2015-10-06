package com.airamerica.products;

import java.util.Date;

import com.airamerica.Airport;
import com.airamerica.utils.Haversine;

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
	
	
	
	public double getFees(Airport [] airportArray){
		String seatType = getFlightClass();
		
		Airport a1 = getDepAirportCode();
		Airport a2 = getArrAirportCode();
		int lat1 = a1.getLattitudeDegrees();
		int latMin1 = a1.getLattitudeMinutes();
		int long1 = a1.getLongitudeDegrees();
		int longMin1 = a1.getLongitudeMinutes();
		int lat2 = a1.getLattitudeDegrees();
		int latMin2 = a1.getLattitudeMinutes();
		int long2 = a1.getLongitudeDegrees();
		int longMin2 =  a1.getLongitudeMinutes();
		double fees = 0;
		
		double miles = Haversine.getMiles(lat1, latMin1, long1, longMin1, lat2, latMin2, long2, longMin2);
		if(seatType.equals("EC")){
			fees = miles * .15;
		}else if(seatType.equals("BC")){
			fees = miles * .5;
		}else{
			fees = miles * .2;
		}
		
		return fees;
	}
	
	
}
