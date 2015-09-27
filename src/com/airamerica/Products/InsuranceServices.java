package com.airamerica.Products;

public class InsuranceServices extends Services {

	private String name;
	private String ageClass;
	private float costPerMile;
	public InsuranceServices(String code, String productType, String name, String ageClass, float costPerMile) {
		super(code, productType);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
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
	
	
}
