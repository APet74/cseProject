package com.airamerica.products;

public class Refreshment extends Service {
	
	private String name;
	private float cost;
	public Refreshment(String code, String productType, String name, float cost) {
		super(code, productType);
		this.name = name;
		this.cost = cost;
	}
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	@Override 
	public double getFees(){
		double cost = getCost();
		return cost;
	}
	
	@Override 
	public double getTax(){
		double cost = getCost();
		double tax = cost * .04;
		return tax;
	}
	
}
