package com.airamerica.Products;

public class SpecialAssistance extends Services {

	
	private String TypeOfService;

	public SpecialAssistance(String code, String productType, String typeOfService) {
		super(code, productType);
		TypeOfService = typeOfService;
	}

	public String getTypeOfService() {
		return TypeOfService;
	}

	public void setTypeOfService(String typeOfService) {
		TypeOfService = typeOfService;
	}
	
	
}
