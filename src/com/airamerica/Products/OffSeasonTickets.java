package com.airamerica.Products;

import java.util.Date;

import com.airamerica.Airports;

public class OffSeasonTickets extends Tickets {
	private Date seasonStartDate, sesaonEndDAte;
	private float rebate;
	public OffSeasonTickets(String code, String productType, Airports depAirportCode, Airports arrAirportCode,
			Date depTime, Date arrTime, String flightNo, String flightClass, String aircraftType, Date seasonStartDate,
			Date sesaonEndDAte, float rebate) {
		super(code, productType, depAirportCode, arrAirportCode, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.seasonStartDate = seasonStartDate;
		this.sesaonEndDAte = sesaonEndDAte;
		this.rebate = rebate;
	}
	public Date getSeasonStartDate() {
		return seasonStartDate;
	}
	public void setSeasonStartDate(Date seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}
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
	
	
}
