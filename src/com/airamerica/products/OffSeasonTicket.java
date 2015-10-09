package com.airamerica.products;

import java.util.Date;

import com.airamerica.Airport;
import com.airamerica.utils.Haversine;

public class OffSeasonTicket extends Ticket {
	private Date seasonStartDate, sesaonEndDAte;
	private float rebate;
	public OffSeasonTicket(String code, String productType, Airport depAirportCode, Airport arrAirportCode,
			Date depTime, Date arrTime, String flightNo, String flightClass, String aircraftType, Date seasonStartDate,
			Date sesaonEndDAte, float rebate) {
		super(code, productType, depAirportCode, arrAirportCode, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.seasonStartDate = seasonStartDate;
		this.sesaonEndDAte = sesaonEndDAte;
		this.rebate = rebate;
	}
	
	@Override
	public Date getSeasonStartDate() {
		return seasonStartDate;
	}
	public void setSeasonStartDate(Date seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}
	
	@Override
	public Date getSesaonEndDAte() {
		return sesaonEndDAte;
	}
	public void setSesaonEndDAte(Date sesaonEndDAte) {
		this.sesaonEndDAte = sesaonEndDAte;
	}
	public float getRebate() {
		return rebate;
	}
	public void setRebate(float rebate) {
		this.rebate = rebate;
	}
	
	@Override
	public double getFees(){
		String seatType = getFlightClass();
		
		Airport a1 = getDepAirportCode();
		Airport a2 = getArrAirportCode();
		int lat1 = a1.getLattitudeDegrees();
		int latMin1 = a1.getLattitudeMinutes();
		int long1 = a1.getLongitudeDegrees();
		int longMin1 = a1.getLongitudeMinutes();
		int lat2 = a2.getLattitudeDegrees();
		int latMin2 = a2.getLattitudeMinutes();
		int long2 = a2.getLongitudeDegrees();
		int longMin2 =  a2.getLongitudeMinutes();
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
	@Override
	public double getTax(double fee){
		double tax = 0;
		tax = fee * .075; // Federal Excise Tax
		return tax;
	}
	
	@Override
	public double getMiles(Airport a1, Airport a2) {

		int lat1 = a1.getLattitudeDegrees();
		int latMin1 = a1.getLattitudeMinutes();
		int long1 = a1.getLongitudeDegrees();
		int longMin1 = a1.getLongitudeMinutes();
		int lat2 = a2.getLattitudeDegrees();
		int latMin2 = a2.getLattitudeMinutes();
		int long2 = a2.getLongitudeDegrees();
		int longMin2 =  a2.getLongitudeMinutes();
		double miles = Haversine.getMiles(lat1, latMin1, long1, longMin1, lat2, latMin2, long2, longMin2);
		return miles;
	}
	
}
