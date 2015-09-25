package com.airamerica;

public class Refreshments extends Services {
	
	private String name;
	private float cost;
	public Refreshments(String code, String productType, String name, float cost) {
		super(code, productType);
		this.name = name;
		this.cost = cost;
	}
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
	
	
}
