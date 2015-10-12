package com.airamerica.products;

public abstract class Service extends Product {
	
	public Service(String code, String productType) {
		super(code, productType);
	}

	public double getFees() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getFees(double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAgeClass(){
		return null;
	}
	
	public float getCostPerMile(){
		return 0;
	}

	public double getTax() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getTax(int amount) {
		// TODO Auto-generated method stub
		return 0;
	}
}
